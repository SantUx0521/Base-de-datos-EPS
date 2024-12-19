package Contenido.Bancos;

import Contenido.ConectividadSQL;
import Contenido.Bancos.Pago;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngresarPago {

    private Connection connection;

    // Constructor que inicializa la conexión con la base de datos
    public IngresarPago() {
        connection = ConectividadSQL.obtenerConexion();  
        if (connection == null) {
            System.out.println("Error: No se pudo establecer la conexión con la base de datos.");
        }
    }

    // Método para mostrar la interfaz gráfica para ingresar pagos
    public void ingresarPago(JFrame ventanaAnterior) {
        // Crear ventana principal
        JFrame frame = new JFrame("Registrar Pago de Aporte");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // Centra la ventana en la pantalla

        // Crear panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));  // Fondo claro

        // Crear GridBagConstraints para un buen diseño
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);  // Espaciado entre componentes

        // Etiquetas
        JLabel lblCodigoPago = new JLabel("Código de Pago:");
        JLabel lblNumRad = new JLabel("Número de Radicado:");
        JLabel lblFechaPago = new JLabel("Fecha de Pago:");
        JLabel lblValor = new JLabel("Valor:");

        // Campos de entrada
        JTextField txtCodigoPago = new JTextField(20);
        JTextField txtNumRad = new JTextField(20);
        JTextField txtFechaPago = new JTextField(20);
        JTextField txtValor = new JTextField(20);

        // Botón de registro
        JButton btnRegistrar = new JButton("Registrar Pago");
        btnRegistrar.setBackground(new Color(60, 179, 113));  // Verde
        btnRegistrar.setForeground(Color.WHITE);

        // Botón de volver
        JButton btnVolver = new JButton("← Volver");
        btnVolver.setBackground(new Color(220, 220, 220));  // Gris claro
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false);

        // Añadir etiquetas y campos de entrada al panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lblCodigoPago, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(txtCodigoPago, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lblNumRad, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(txtNumRad, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(lblFechaPago, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(txtFechaPago, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(lblValor, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(txtValor, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(btnRegistrar, constraints);

        frame.add(panel);

        // Panel superior para incluir el botón de volver
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(245, 245, 245));
        topPanel.add(btnVolver);
        frame.add(topPanel, BorderLayout.NORTH);

        // Acción del botón de registro
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los campos de texto
                try {
                    // Validación de Código de Pago
                    int codigoPago = Integer.parseInt(txtCodigoPago.getText());

                    // Validación de Número de Radicado
                    int numRadicado = Integer.parseInt(txtNumRad.getText());

                    // Validación de la fecha de pago
                    Date fechaPago = validateDate(txtFechaPago.getText());

                    // Validación del Valor
                    double valor = Double.parseDouble(txtValor.getText());

                    // Crear objeto Pago
                    Pago pago = new Pago(codigoPago, numRadicado, fechaPago, valor);

                    // Llamar al método registrarPagos para insertar en la base de datos
                    if (registrarPagos(pago)) {
                        JOptionPane.showMessageDialog(frame, "Pago registrado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error al registrar el pago.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese datos válidos.");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, "Error en la fecha, asegúrese de usar el formato correcto (YYYY-MM-DD).");
                }
            }
        });

        // Acción del botón de volver
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cierra la ventana actual
                if (ventanaAnterior != null) {
                    ventanaAnterior.setVisible(true); // Vuelve a la ventana anterior
                }
            }
        });

        // Mostrar la ventana
        frame.setVisible(true);
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

    // Método para validar que la fecha está en formato correcto (YYYY-MM-DD)
    private Date validateDate(String dateString) throws IllegalArgumentException {
        try {
            return Date.valueOf(dateString); // Convierte el String a Date
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Fecha no válida.");
        }
    }
}
