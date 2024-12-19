package Contenido.Cotizantes;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import Contenido.ConectividadSQL;

public class info_cotizantes {
    private Connection connection;

    // Constructor que inicializa la conexión a la base de datos.
    public info_cotizantes() {
        connection = ConectividadSQL.obtenerConexion();
        if (connection == null) {
            System.out.println("Error: No se pudo establecer la conexión con la base de datos.");
        }
    }

    // Método principal para seleccionar cotizantes y mostrar ventana
    public void seleccionarCotizanteSwing() {
        // Crear ventana principal con un diseño bonito
        JFrame frame = new JFrame("Detalles del Cotizante");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Centra la ventana

        // Panel superior con un borde y espacio
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(45, 45, 45)); // Color de fondo
        topPanel.setLayout(new FlowLayout());
        topPanel.setPreferredSize(new Dimension(frame.getWidth(), 50));
        
        JLabel lblDocumento = new JLabel("Número de Documento:");
        lblDocumento.setForeground(Color.WHITE);  // Color blanco para la etiqueta
        lblDocumento.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(lblDocumento);

        JTextField txtDocumento = new JTextField(15);
        topPanel.add(txtDocumento);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(60, 179, 113)); // Verde
        btnBuscar.setForeground(Color.WHITE); // Color blanco
        topPanel.add(btnBuscar);

        frame.add(topPanel, BorderLayout.NORTH);

        // Crear JTable para mostrar los resultados en formato de tabla
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Configurar la tabla con un modelo predeterminado
        DefaultTableModel tableModel = new DefaultTableModel();
        table.setModel(tableModel);

        // Definir columnas para la tabla
        tableModel.addColumn("Número Documento");
        tableModel.addColumn("Tipo Documento");
        tableModel.addColumn("Fecha Afiliación");
        tableModel.addColumn("Salario");
        tableModel.addColumn("Apellidos");
        tableModel.addColumn("Nombres");
        tableModel.addColumn("Fecha Nacimiento");
        tableModel.addColumn("Género");
        tableModel.addColumn("Dirección");
        tableModel.addColumn("Teléfono");
        tableModel.addColumn("Ciudad");
        tableModel.addColumn("Estado Civil");
        tableModel.addColumn("Correo");
        tableModel.addColumn("Estado");
        tableModel.addColumn("NIT IPS");

        // Agregar espacio de texto para mostrar el mensaje si no se encuentran datos
        JTextArea txtAreaMessage = new JTextArea();
        txtAreaMessage.setEditable(false);
        txtAreaMessage.setBackground(new Color(255, 255, 255));
        txtAreaMessage.setForeground(Color.RED);
        frame.add(txtAreaMessage, BorderLayout.SOUTH);

        // Acción para el botón "Buscar"
        btnBuscar.addActionListener(e -> {
            String numDocStr = txtDocumento.getText();
            if (numDocStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, ingrese un número de documento.");
                return;
            }

            try {
                int numDoc = Integer.parseInt(numDocStr);
                buscarCotizante(numDoc, tableModel, txtAreaMessage);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "El número de documento debe ser numérico.");
            }
        });

        // Mostrar la ventana
        frame.setVisible(true);
    }

    // Método para buscar y mostrar los detalles del cotizante en la tabla
    private void buscarCotizante(int numDoc, DefaultTableModel tableModel, JTextArea txtAreaMessage) {
        // Limpiar la tabla antes de mostrar los nuevos resultados
        tableModel.setRowCount(0);

        // Consulta SQL para obtener información del cotizante y afiliado
        String query = "SELECT c.num_doc, c.tipo_doc, c.fecha_afil, c.salario, " +
                "a.apellidos, a.nombres, a.fecha_nac, a.genero, " +
                "a.direccion, a.telefono, a.ciudad, a.estado_civil, " +
                "a.correo, a.estado, a.nit_ips " +
                "FROM cotizante c " +
                "INNER JOIN afiliado a ON c.num_doc = a.num_doc " +
                "WHERE c.num_doc = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, numDoc);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    // Agregar una fila con los detalles del cotizante
                    tableModel.addRow(new Object[]{
                            rs.getInt("num_doc"),
                            rs.getString("tipo_doc"),
                            rs.getDate("fecha_afil"),
                            rs.getDouble("salario"),
                            rs.getString("apellidos"),
                            rs.getString("nombres"),
                            rs.getString("fecha_nac"),
                            rs.getString("genero"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("ciudad"),
                            rs.getString("estado_civil"),
                            rs.getString("correo"),
                            rs.getString("estado"),
                            rs.getInt("nit_ips")
                    });

                    txtAreaMessage.setText("");
                } else {
                    // Si no se encuentra el cotizante, mostrar un mensaje
                    txtAreaMessage.setText("No se encontró información para el documento ingresado.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener los detalles del cotizante: " + ex.getMessage());
        }
    }
}
