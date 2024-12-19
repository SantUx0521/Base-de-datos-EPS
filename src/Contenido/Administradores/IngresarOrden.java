package Contenido.Administradores;

import Contenido.ConectividadSQL;
import Contenido.Administradores.Ordenes_servicio;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class IngresarOrden {
    private Connection connection;

    public IngresarOrden() {
        connection = ConectividadSQL.obtenerConexion();  
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("Error en la conexión a la base de datos.");
        }
    }

    public void GenerarOrden() {
        JFrame frame = new JFrame("Registrar Orden de Servicio");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblCodOrden = new JLabel("Código de Orden:");
        JLabel lblFecha = new JLabel("Fecha de Orden:");
        JLabel lblDocPaciente = new JLabel("Documento del Paciente:");
        JLabel lblMedico = new JLabel("Médico:");
        JLabel lblDiagnostico = new JLabel("Diagnóstico:");
        
        JTextField txtCodOrden = new JTextField();
        JTextField txtFecha = new JTextField();
        JTextField txtDocPaciente = new JTextField();
        JTextField txtMedico = new JTextField();
        JTextField txtDiagnostico = new JTextField();
        
        JButton btnRegistrar = new JButton("Registrar Orden");
        btnRegistrar.setBackground(new Color(102, 180, 255));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setFocusPainted(false);

        panel.add(lblCodOrden);
        panel.add(txtCodOrden);
        panel.add(lblFecha);
        panel.add(txtFecha);
        panel.add(lblDocPaciente);
        panel.add(txtDocPaciente);
        panel.add(lblMedico);
        panel.add(txtMedico);
        panel.add(lblDiagnostico);
        panel.add(txtDiagnostico);
        panel.add(Box.createVerticalStrut(20)); 
        panel.add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {
            try {
                int codOrden = Integer.parseInt(txtCodOrden.getText());
                Date fecha = Date.valueOf(txtFecha.getText());
                int docPaciente = Integer.parseInt(txtDocPaciente.getText());
                String medico = txtMedico.getText();
                String diagnostico = txtDiagnostico.getText();
                
                if (codOrden == 0 || fecha == null || docPaciente == 0 || medico.isEmpty() || diagnostico.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.");
                } else {

                    Ordenes_servicio nuevaOrden = new Ordenes_servicio(codOrden, fecha, docPaciente, medico, diagnostico);
                    RegistrarOrden(nuevaOrden);

                    txtCodOrden.setText("");
                    txtFecha.setText("");
                    txtDocPaciente.setText("");
                    txtMedico.setText("");
                    txtDiagnostico.setText("");

                    JOptionPane.showMessageDialog(frame, "Orden registrada exitosamente.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error al registrar la orden: " + ex.getMessage());
            }
        });


        frame.add(panel);
        frame.setVisible(true);
    }

    public void RegistrarOrden(Ordenes_servicio orden) {
        String query = "INSERT INTO ordenes (cod_orden, fecha, doc_paciente, medico, diagnostico) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orden.getCod_orden());
            statement.setDate(2, orden.getFecha());
            statement.setInt(3, orden.getDoc_paciente());
            statement.setString(4, orden.getMedico());
            statement.setString(5, orden.getDiagnostico());
            statement.executeUpdate();
            System.out.println("Orden agregada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar la Orden: " + e.getMessage());
        }
    }
}
