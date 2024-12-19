package Contenido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import Contenido.ConectividadSQL;

public class ModificarContratoGUI extends JFrame {
    private Connection connection;
    private JTextField tfRadicado, tfFieldValue;
    private JComboBox<String> comboFields;
    private JButton btnUpdate, btnCancel;
    private JLabel lblStatus;

    public ModificarContratoGUI() {
        connection = ConectividadSQL.obtenerConexion();
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }

        // Configuración de la ventana
        setTitle("Modificar Contrato");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana
        setResizable(false); // No se puede redimensionar la ventana
        setBackground(Color.WHITE);

        // Panel principal con BorderLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes

        // Sub-panel para el formulario con BoxLayout vertical
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);  // Hacer el panel transparente
        panel.add(formPanel, BorderLayout.CENTER);

        // Estilo de los componentes
        Font font = new Font("Arial", Font.PLAIN, 14);

        // Título
        JLabel lblTitle = new JLabel("Modificar Contrato");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(lblTitle);

        // Campo: Número de radicado
        JLabel lblRadicado = new JLabel("Número de radicado del contrato:");
        lblRadicado.setFont(font);
        tfRadicado = new JTextField(20);
        tfRadicado.setFont(font);
        tfRadicado.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        tfRadicado.setMargin(new Insets(5, 10, 5, 10));

        // Campo: Campo a modificar
        JLabel lblField = new JLabel("Campo a modificar:");
        lblField.setFont(font);
        comboFields = new JComboBox<>(new String[] {
            "Fecha del Reporte", "Salario Base del Cotizante", "Estado del Contrato"
        });
        comboFields.setFont(font);
        comboFields.setBackground(new Color(224, 224, 224));
        comboFields.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Campo: Nuevo valor
        JLabel lblFieldValue = new JLabel("Nuevo valor:");
        lblFieldValue.setFont(font);
        tfFieldValue = new JTextField(20);
        tfFieldValue.setFont(font);
        tfFieldValue.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        tfFieldValue.setMargin(new Insets(5, 10, 5, 10));

        // Botón Actualizar
        btnUpdate = new JButton("Actualizar");
        btnUpdate.setFont(font);
        btnUpdate.setBackground(new Color(0, 150, 136)); // Color verde suave
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Botón Cancelar
        btnCancel = new JButton("Cancelar");
        btnCancel.setFont(font);
        btnCancel.setBackground(new Color(255, 87, 34)); // Color rojo suave
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFocusPainted(false);
        btnCancel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Estado
        lblStatus = new JLabel(" ");
        lblStatus.setFont(new Font("Arial", Font.ITALIC, 12));
        lblStatus.setForeground(Color.RED);
        lblStatus.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agregar componentes al panel de formulario
        formPanel.add(lblRadicado);
        formPanel.add(tfRadicado);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(lblField);
        formPanel.add(comboFields);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(lblFieldValue);
        formPanel.add(tfFieldValue);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(btnUpdate);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(btnCancel);
        formPanel.add(lblStatus);

        // Añadir panel al JFrame
        add(panel);

        // Eventos
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarContrato();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarEdicion();
            }
        });
    }

    private void actualizarContrato() {
        String radicadoStr = tfRadicado.getText();
        String field = (String) comboFields.getSelectedItem();
        String newValue = tfFieldValue.getText();

        if (radicadoStr.isEmpty() || newValue.isEmpty()) {
            lblStatus.setText("Por favor ingrese todos los campos.");
            return;
        }

        try {
            int radicado = Integer.parseInt(radicadoStr);
            String fieldDb = getFieldDbName(field);

            if (fieldDb != null) {
                String query;
                
                // Si el campo es "Salario Base del Cotizante", se debe manejar como un doble
                if ("salario_base".equals(fieldDb)) {
                    double salario = Double.parseDouble(newValue);
                    query = "UPDATE contrato SET " + fieldDb + " = ? WHERE num_radicado = ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setDouble(1, salario);  // Asignar el nuevo valor como Double
                        statement.setInt(2, radicado); 

                        int rowsUpdated = statement.executeUpdate();
                        if (rowsUpdated > 0) {
                            lblStatus.setText("Modificación exitosa.");
                            lblStatus.setForeground(new Color(76, 175, 80)); // Verde éxito
                        } else {
                            lblStatus.setText("No se encontró un contrato con el radicado ingresado.");
                            lblStatus.setForeground(Color.RED);
                        }
                    }
                } else {
                    // Para otros campos, tratamos el nuevo valor como String
                    query = "UPDATE contrato SET " + fieldDb + " = ? WHERE num_radicado = ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, newValue); // Asignar el nuevo valor para otros campos
                        statement.setInt(2, radicado); // Asignar el número de radicado

                        int rowsUpdated = statement.executeUpdate();
                        if (rowsUpdated > 0) {
                            lblStatus.setText("Modificación exitosa.");
                            lblStatus.setForeground(new Color(76, 175, 80)); // Verde éxito
                        } else {
                            lblStatus.setText("No se encontró un contrato con el radicado ingresado.");
                            lblStatus.setForeground(Color.RED);
                        }
                    }
                }
            } else {
                lblStatus.setText("Opción no válida.");
                lblStatus.setForeground(Color.RED);
            }
        } catch (NumberFormatException ex) {
            lblStatus.setText("El radicado debe ser un número válido o el salario debe ser un valor decimal.");
            lblStatus.setForeground(Color.RED);
        } catch (SQLException ex) {
            lblStatus.setText("Error al modificar: " + ex.getMessage());
            lblStatus.setForeground(Color.RED);
        }
    }

    private void cancelarEdicion() {
        tfRadicado.setText("");
        tfFieldValue.setText("");
        lblStatus.setText(" ");
    }

    private String getFieldDbName(String field) {
        switch (field) {
            case "Fecha del Reporte": return "fecha_rec";
            case "Salario Base del Cotizante": return "salario_base";
            case "Estado del Contrato": return "estado";
            default: return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ModificarContratoGUI().setVisible(true);
            }
        });
    }
}
