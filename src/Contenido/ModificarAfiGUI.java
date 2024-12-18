package Contenido;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import Contenido.ConectividadSQL;

public class ModificarAfiGUI extends JFrame {
    private Connection connection;
    private JTextField tfNumDoc, tfFieldValue;
    private JComboBox<String> comboFields;
    private JButton btnUpdate, btnCancel;
    private JLabel lblStatus;
    
    public ModificarAfiGUI() {
        connection = ConectividadSQL.obtenerConexion();
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }

        setTitle("Modificar Afiliado");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centra la ventana

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        // Componentes del formulario
        JLabel lblNumDoc = new JLabel("Número de Documento:");
        tfNumDoc = new JTextField(20);

        JLabel lblField = new JLabel("Campo a modificar:");
        comboFields = new JComboBox<>(new String[]{
            "Tipo de documento", "Apellidos", "Nombres", "Fecha de nacimiento", "Género",
            "Dirección", "Teléfono", "Ciudad", "Estado Civil", "Correo", "Estado", "Nit de la IPS"
        });

        JLabel lblFieldValue = new JLabel("Nuevo valor:");
        tfFieldValue = new JTextField(20);

        btnUpdate = new JButton("Actualizar");
        btnCancel = new JButton("Cancelar");
        
        lblStatus = new JLabel(" ");
        
        // Añadir los componentes al panel
        panel.add(lblNumDoc);
        panel.add(tfNumDoc);
        panel.add(lblField);
        panel.add(comboFields);
        panel.add(lblFieldValue);
        panel.add(tfFieldValue);
        panel.add(btnUpdate);
        panel.add(btnCancel);
        panel.add(lblStatus);

        add(panel);

        // Eventos
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numDocStr = tfNumDoc.getText();
                String field = (String) comboFields.getSelectedItem();
                String newValue = tfFieldValue.getText();

                if (numDocStr.isEmpty() || newValue.isEmpty()) {
                    lblStatus.setText("Por favor ingrese todos los campos.");
                    return;
                }

                int numDoc = Integer.parseInt(numDocStr);

                String fieldDb = getFieldDbName(field);
                if (fieldDb != null) {
                    try {
                        String query = "UPDATE afiliado SET " + fieldDb + " = ? WHERE num_doc = ?";
                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            if ("fecha_nac".equals(fieldDb)) {
                                statement.setDate(1, Date.valueOf(newValue));
                            } else if ("nit_ips".equals(fieldDb)) {
                                statement.setInt(1, Integer.parseInt(newValue));
                            } else {
                                statement.setString(1, newValue);
                            }
                            statement.setInt(2, numDoc);

                            int rowsUpdated = statement.executeUpdate();
                            if (rowsUpdated > 0) {
                                lblStatus.setText("Modificación exitosa.");
                            } else {
                                lblStatus.setText("No se encontró un afiliado con ese número de documento.");
                            }
                        }
                    } catch (SQLException ex) {
                        lblStatus.setText("Error al modificar: " + ex.getMessage());
                    }
                } else {
                    lblStatus.setText("Opción no válida.");
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfNumDoc.setText("");
                tfFieldValue.setText("");
                lblStatus.setText(" ");
            }
        });
    }

    private String getFieldDbName(String field) {
        switch (field) {
            case "Tipo de documento": return "tipo_doc";
            case "Apellidos": return "apellidos";
            case "Nombres": return "nombres";
            case "Fecha de nacimiento": return "fecha_nac";
            case "Género": return "genero";
            case "Dirección": return "direccion";
            case "Teléfono": return "telefono";
            case "Ciudad": return "ciudad";
            case "Estado Civil": return "estado_civil";
            case "Correo": return "correo";
            case "Estado": return "estado";
            case "Nit de la IPS": return "nit_ips";
            default: return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ModificarAfiGUI().setVisible(true);
            }
        });
    }
}