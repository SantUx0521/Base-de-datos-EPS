package Contenido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import Contenido.Administradores.Empresa;

public class IngresarEmpGUI extends JFrame {
    private Connection connection;
    
    private JTextField txtNit;
    private JTextField txtRazonSocial;
    private JTextField txtCiudad;
    private JTextField txtDireccion;
    private JTextField txtNombreContacto;
    private JButton btnRegistrar;
    private JLabel lblStatus;

    public IngresarEmpGUI() {
        connection = ConectividadSQL.obtenerConexion();
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("Error en la conexión a la base de datos.");
        }
        
        setTitle("Registro de Empresa");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centrar la ventana
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        
        JLabel lblNit = new JLabel("NIT:");
        JLabel lblRazonSocial = new JLabel("Razón Social:");
        JLabel lblCiudad = new JLabel("Ciudad:");
        JLabel lblDireccion = new JLabel("Dirección:");
        JLabel lblNombreContacto = new JLabel("Nombre Contacto:");

        txtNit = new JTextField();
        txtRazonSocial = new JTextField();
        txtCiudad = new JTextField();
        txtDireccion = new JTextField();
        txtNombreContacto = new JTextField();

        btnRegistrar = new JButton("Registrar Empresa");
        lblStatus = new JLabel("", JLabel.CENTER);
        lblStatus.setForeground(Color.RED);

        panel.add(lblNit);
        panel.add(txtNit);
        panel.add(lblRazonSocial);
        panel.add(txtRazonSocial);
        panel.add(lblCiudad);
        panel.add(txtCiudad);
        panel.add(lblDireccion);
        panel.add(txtDireccion);
        panel.add(lblNombreContacto);
        panel.add(txtNombreContacto);
        panel.add(new JLabel());
        panel.add(btnRegistrar);

        add(panel, BorderLayout.CENTER);
        add(lblStatus, BorderLayout.SOUTH);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEmpresa();
            }
        });
    }

    public boolean registrarEmpresa(Empresa empresa) {
        String query = "INSERT INTO EMPRESA (e_nit, razon_social, ciudad, direccion, nombre_contacto) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, empresa.getNit());
            statement.setString(2, empresa.getRazonSocial());
            statement.setString(3, empresa.getCiudad());
            statement.setString(4, empresa.getDireccion());
            statement.setString(5, empresa.getNombreContacto());

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                lblStatus.setText("Empresa registrada correctamente.");
                lblStatus.setForeground(Color.GREEN);
                return true;
            } else {
                lblStatus.setText("No se pudo registrar la empresa.");
                lblStatus.setForeground(Color.RED);
                return false;
            }
        } catch (SQLException e) {
            lblStatus.setText("Error al registrar la empresa: " + e.getMessage());
            lblStatus.setForeground(Color.RED);
            return false;
        }
    }

    public void registrarEmpresa() {
        try {
            int nit = Integer.parseInt(txtNit.getText());
            String razonSocial = txtRazonSocial.getText();
            String ciudad = txtCiudad.getText();
            String direccion = txtDireccion.getText();
            String nombreContacto = txtNombreContacto.getText();

            if (nit == 0 || razonSocial.isEmpty() || ciudad.isEmpty() || direccion.isEmpty() || nombreContacto.isEmpty()) {
                lblStatus.setText("Todos los campos son obligatorios.");
                lblStatus.setForeground(Color.RED);
                return;
            }

            Empresa empresa = new Empresa(nit, razonSocial, ciudad, direccion, nombreContacto);
            registrarEmpresa(empresa);
        } catch (NumberFormatException e) {
            lblStatus.setText("El NIT debe ser un número válido.");
            lblStatus.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new IngresarEmpGUI().setVisible(true);
        });
    }
}
