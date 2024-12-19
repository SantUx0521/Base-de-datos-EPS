package Contenido.Administradores;

import Contenido.ConectividadSQL;
import Contenido.Administradores.ips;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class IngresarIPS {
    private Connection connection;

    // Constructor que establece la conexión a la base de datos
    public IngresarIPS() {
        connection = ConectividadSQL.obtenerConexion();
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("Error en la conexión a la base de datos.");
        }
    }

    // Método que crea y muestra la ventana para registrar la IPS
    public void agregarIps() {
        JFrame frame = new JFrame("Registrar IPS");
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centra la ventana

        // Panel principal con diseño de BoxLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Componentes gráficos
        JLabel lblNit = new JLabel("NIT de la IPS:");
        JLabel lblRazonSocial = new JLabel("Razón Social:");
        JLabel lblNivelAtencion = new JLabel("Nivel de Atención:");
        
        JTextField txtNit = new JTextField();
        JTextField txtRazonSocial = new JTextField();
        JTextField txtNivelAtencion = new JTextField();
        
        JButton btnRegistrar = new JButton("Registrar IPS");
        btnRegistrar.setBackground(new Color(38, 94, 121)); // Color azul suave
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));

        // Añadiendo los componentes al panel
        panel.add(lblNit);
        panel.add(txtNit);
        panel.add(lblRazonSocial);
        panel.add(txtRazonSocial);
        panel.add(lblNivelAtencion);
        panel.add(txtNivelAtencion);
        panel.add(Box.createVerticalStrut(20)); // Espaciado entre los elementos
        panel.add(btnRegistrar);

        // Acción de registro
        btnRegistrar.addActionListener(e -> {
            try {
                int nit = Integer.parseInt(txtNit.getText());
                String razonSocial = txtRazonSocial.getText();
                String nivelAtencion = txtNivelAtencion.getText();
                
                if (nit == 0 || razonSocial.isEmpty() || nivelAtencion.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.");
                } else {
                    // Llamar a la función de registrarIPS
                    ips nuevaIps = new ips(nit, razonSocial, nivelAtencion);
                    registrarIPS(nuevaIps);

                    // Limpiar campos después del registro
                    txtNit.setText("");
                    txtRazonSocial.setText("");
                    txtNivelAtencion.setText("");

                    JOptionPane.showMessageDialog(frame, "IPS registrada exitosamente.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "El NIT debe ser un número válido.");
            }
        });

        // Establecer la ventana visible
        frame.add(panel);
        frame.setVisible(true);
    }

    // Método para registrar una IPS en la base de datos
    public void registrarIPS(ips Ips) {
        String query = "INSERT INTO ips (nit, razon_social, nivel_atencion) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, Ips.getNit());
            statement.setString(2, Ips.getRazonSocial());
            statement.setString(3, Ips.getNivelAtencion());
            statement.executeUpdate();
            System.out.println("Ips agregada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar la Ips: " + e.getMessage());
        }
    }
}
