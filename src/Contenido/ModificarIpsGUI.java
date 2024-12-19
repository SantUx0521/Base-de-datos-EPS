package Contenido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import Contenido.ConectividadSQL;

public class ModificarIpsGUI extends JFrame {
    private Connection connection;
    private JTextField tfNit, tfFieldValue;
    private JComboBox<String> comboFields;
    private JButton btnUpdate, btnCancel;
    private JLabel lblStatus;

    public ModificarIpsGUI() {
        connection = ConectividadSQL.obtenerConexion();
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }

        // Configuración de la ventana
        setTitle("Modificar IPS");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana
        setResizable(false); // No se puede redimensionar la ventana

        // Panel principal con BoxLayout para diseño vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes alrededor

        // Estilo de los componentes
        Font font = new Font("Arial", Font.PLAIN, 14);

        // Componentes del formulario
        JLabel lblNit = new JLabel("NIT de la IPS a modificar:");
        lblNit.setFont(font);
        tfNit = new JTextField(20);
        tfNit.setFont(font);

        JLabel lblField = new JLabel("Campo a modificar:");
        lblField.setFont(font);
        comboFields = new JComboBox<>(new String[]{
            "Razón Social", "Nivel de Atención"
        });
        comboFields.setFont(font);

        JLabel lblFieldValue = new JLabel("Nuevo valor:");
        lblFieldValue.setFont(font);
        tfFieldValue = new JTextField(20);
        tfFieldValue.setFont(font);

        btnUpdate = new JButton("Actualizar");
        btnUpdate.setFont(font);
        btnUpdate.setBackground(new Color(0, 150, 136)); // Color verde suave
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setBorderPainted(false);

        btnCancel = new JButton("Cancelar");
        btnCancel.setFont(font);
        btnCancel.setBackground(new Color(255, 87, 34)); // Color rojo suave
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFocusPainted(false);
        btnCancel.setBorderPainted(false);

        lblStatus = new JLabel(" ");
        lblStatus.setFont(font);
        lblStatus.setForeground(Color.RED);

        // Añadir los componentes al panel
        panel.add(lblNit);
        panel.add(tfNit);
        panel.add(lblField);
        panel.add(comboFields);
        panel.add(lblFieldValue);
        panel.add(tfFieldValue);
        panel.add(btnUpdate);
        panel.add(btnCancel);
        panel.add(lblStatus);

        // Añadir panel al JFrame
        add(panel);

        // Eventos
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nitStr = tfNit.getText();
                String field = (String) comboFields.getSelectedItem();
                String newValue = tfFieldValue.getText();

                if (nitStr.isEmpty() || newValue.isEmpty()) {
                    lblStatus.setText("Por favor ingrese todos los campos.");
                    return;
                }

                int nit = Integer.parseInt(nitStr);
                String fieldDb = getFieldDbName(field);

                if (fieldDb != null) {
                    try {
                        String query = "UPDATE IPS SET " + fieldDb + " = ? WHERE NIT = ?";
                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            statement.setString(1, newValue); // Asignar el nuevo valor para el campo
                            statement.setInt(2, nit); // Asignar el NIT de la IPS

                            int rowsUpdated = statement.executeUpdate();
                            if (rowsUpdated > 0) {
                                lblStatus.setText("Modificación exitosa.");
                                lblStatus.setForeground(Color.GREEN);
                            } else {
                                lblStatus.setText("No se encontró una IPS con el NIT ingresado.");
                                lblStatus.setForeground(Color.RED);
                            }
                        }
                    } catch (SQLException ex) {
                        lblStatus.setText("Error al modificar: " + ex.getMessage());
                        lblStatus.setForeground(Color.RED);
                    }
                } else {
                    lblStatus.setText("Opción no válida.");
                    lblStatus.setForeground(Color.RED);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfNit.setText("");
                tfFieldValue.setText("");
                lblStatus.setText(" ");
            }
        });
    }

    private String getFieldDbName(String field) {
        switch (field) {
            case "Razón Social": return "razon_social";
            case "Nivel de Atención": return "nivel_atencion";
            default: return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ModificarIpsGUI().setVisible(true);
            }
        });
    }
}
