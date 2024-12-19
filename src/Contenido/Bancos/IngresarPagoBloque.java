package Contenido.Bancos;

import Contenido.ConectividadSQL;
import Contenido.Bancos.Pago;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngresarPagoBloque {
    private Connection connection;

    // Constructor que inicializa la conexión con la base de datos
    public IngresarPagoBloque() {
        connection = ConectividadSQL.obtenerConexion();
        if (connection == null) {
            System.out.println("Error: No se pudo establecer la conexión con la base de datos.");
        }
    }

    // Método para mostrar la interfaz gráfica para cargar pagos desde un archivo
    public void cargarPagosDesdeArchivo(JFrame ventanaAnterior) {
        // Crear ventana principal
        JFrame frame = new JFrame("Registrar Pagos desde Archivo");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Crear panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245)); // Fondo claro

        // Crear GridBagConstraints para un buen diseño
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes

        // Botón de volver
        JButton btnVolver = new JButton("⬅ Volver");
        btnVolver.setFont(new Font("Arial", Font.PLAIN, 12));
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setBackground(new Color(230, 230, 230)); // Fondo gris claro
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(btnVolver, constraints);

        // Etiqueta y campo para seleccionar archivo
        JLabel lblArchivo = new JLabel("Selecciona el Archivo de Pagos:");
        JTextField txtArchivo = new JTextField(20);
        JButton btnSeleccionarArchivo = new JButton("Seleccionar Archivo");

        // Botón para procesar el archivo
        JButton btnCargar = new JButton("Cargar Pagos");
        btnCargar.setBackground(new Color(60, 179, 113)); // Verde
        btnCargar.setForeground(Color.WHITE);

        // Añadir componentes al panel
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(lblArchivo, constraints);

        constraints.gridx = 1;
        panel.add(txtArchivo, constraints);

        constraints.gridx = 2;
        panel.add(btnSeleccionarArchivo, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        panel.add(btnCargar, constraints);

        frame.add(panel);

        // Acción de seleccionar archivo
        btnSeleccionarArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    txtArchivo.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        // Acción del botón Cargar
        btnCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String archivo = txtArchivo.getText();
                if (archivo.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, seleccione un archivo.");
                } else {
                    // Procesar archivo y registrar pagos
                    if (procesarArchivo(archivo)) {
                        JOptionPane.showMessageDialog(frame, "Pagos registrados exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error al registrar los pagos.");
                    }
                }
            }
        });

        // Acción del botón Volver
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cerrar la ventana actual
                if (ventanaAnterior != null) {
                    ventanaAnterior.setVisible(true); // Volver a la ventana anterior
                }
            }
        });

        // Mostrar la ventana
        frame.setVisible(true);
    }

    // Método para procesar el archivo y registrar los pagos
    private boolean procesarArchivo(String archivo) {
        BufferedReader br = null;
        String linea;
        boolean exitoso = true;

        try {
            br = new BufferedReader(new FileReader(archivo));
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                if (datos.length == 4) {
                    try {
                        // Extraer los datos de la línea
                        int codigoPago = Integer.parseInt(datos[0]);
                        int numRadicado = Integer.parseInt(datos[1]);
                        Date fechaPago = Date.valueOf(datos[2]); // Formato YYYY-MM-DD
                        double valor = Double.parseDouble(datos[3]);

                        // Crear objeto Pago
                        Pago pago = new Pago(codigoPago, numRadicado, fechaPago, valor);

                        // Registrar el pago en la base de datos
                        if (!registrarPagos(pago)) {
                            exitoso = false;
                            break;
                        }
                    } catch (RuntimeException e) {
                        JOptionPane.showMessageDialog(null, "Error en los datos: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
            exitoso = false;
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return exitoso;
    }

    // Método para registrar el pago en la base de datos
    private boolean registrarPagos(Pago pago) {
        String query = "INSERT INTO pago_aporte (codigo_pago, num_rad, fecha_pago, valor) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, pago.getCodigoPago());
            statement.setInt(2, pago.getNumRadicado());
            statement.setDate(3, pago.getFechaPago());
            statement.setDouble(4, pago.getValor());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el pago: " + e.getMessage());
            return false;
        }
    }
}