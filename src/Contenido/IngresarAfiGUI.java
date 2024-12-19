package Contenido;

import Contenido.ConectividadSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class IngresarAfiGUI {
    private Connection connection;

    private JFrame frame;
    private JTextField numDocField, tipoDocField, apellidosField, nombresField, fechaField, generoField,
            direccionField, telefonoField, ciudadField, civilField, correoField, estadoField, nitIpsField;
    private JButton agregarButton;

    public IngresarAfiGUI() {
        connection = ConectividadSQL.obtenerConexion(); // Establece la conexión con la base de datos
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        }
        initUI();
    }

    private void initUI() {
        // Configurar la ventana principal
        frame = new JFrame("Ingreso de Afiliado");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        frame.setResizable(false); // No permite redimensionar la ventana

        // Crear el panel de contenido con márgenes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(14, 2, 10, 20)); // GridLayout para alinear campos
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Añade márgenes

        // Crear los campos de texto y etiquetas
        JLabel numDocLabel = new JLabel("Número de Documento:");
        numDocField = new JTextField();
        JLabel tipoDocLabel = new JLabel("Tipo de Documento:");
        tipoDocField = new JTextField();
        JLabel apellidosLabel = new JLabel("Apellidos:");
        apellidosField = new JTextField();
        JLabel nombresLabel = new JLabel("Nombres:");
        nombresField = new JTextField();
        JLabel fechaLabel = new JLabel("Fecha de Nacimiento (YYYY-MM-DD):");
        fechaField = new JTextField();
        JLabel generoLabel = new JLabel("Género:");
        generoField = new JTextField();
        JLabel direccionLabel = new JLabel("Dirección:");
        direccionField = new JTextField();
        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoField = new JTextField();
        JLabel ciudadLabel = new JLabel("Ciudad:");
        ciudadField = new JTextField();
        JLabel civilLabel = new JLabel("Estado Civil:");
        civilField = new JTextField();
        JLabel correoLabel = new JLabel("Correo:");
        correoField = new JTextField();
        JLabel estadoLabel = new JLabel("Estado (Activo/Inactivo/Retirado):");
        estadoField = new JTextField();
        JLabel nitIpsLabel = new JLabel("NIT IPS:");
        nitIpsField = new JTextField();

        // Agregar campos al panel
        panel.add(numDocLabel);
        panel.add(numDocField);
        panel.add(tipoDocLabel);
        panel.add(tipoDocField);
        panel.add(apellidosLabel);
        panel.add(apellidosField);
        panel.add(nombresLabel);
        panel.add(nombresField);
        panel.add(fechaLabel);
        panel.add(fechaField);
        panel.add(generoLabel);
        panel.add(generoField);
        panel.add(direccionLabel);
        panel.add(direccionField);
        panel.add(telefonoLabel);
        panel.add(telefonoField);
        panel.add(ciudadLabel);
        panel.add(ciudadField);
        panel.add(civilLabel);
        panel.add(civilField);
        panel.add(correoLabel);
        panel.add(correoField);
        panel.add(estadoLabel);
        panel.add(estadoField);
        panel.add(nitIpsLabel);
        panel.add(nitIpsField);

        // Estilo del botón
        agregarButton = new JButton("Agregar Afiliado");
        agregarButton.setFont(new Font("Arial", Font.BOLD, 14));
        agregarButton.setBackground(new Color(51, 204, 255)); // Color de fondo
        agregarButton.setForeground(Color.WHITE); // Color de texto
        agregarButton.setFocusPainted(false);
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarAfiliado();
            }
        });

        // Crear panel inferior con el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agregarButton);

        // Agregar paneles a la ventana principal
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Mostrar la ventana
        frame.setVisible(true);
    }

    public void agregarAfiliado() {
        try {
            // Obtener los datos de los campos de texto
            int numDoc = Integer.parseInt(numDocField.getText());
            String tipoDoc = tipoDocField.getText();
            String apellidos = apellidosField.getText();
            String nombres = nombresField.getText();
            String fecha = fechaField.getText();
            String genero = generoField.getText();
            String direccion = direccionField.getText();
            String telefono = telefonoField.getText();
            String ciudad = ciudadField.getText();
            String civil = civilField.getText();
            String correo = correoField.getText();
            String estado = estadoField.getText();
            int nitIps = Integer.parseInt(nitIpsField.getText());

            // Insertar el afiliado en la base de datos
            String query = "INSERT INTO Afiliado (Num_Doc, Tipo_Doc, Apellidos, Nombres, Fecha_Nac, Genero, Direccion, Telefono, Ciudad, Estado_Civil, Correo, Estado, Nit_ips) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, numDoc);
                statement.setString(2, tipoDoc);
                statement.setString(3, apellidos);
                statement.setString(4, nombres);
                statement.setDate(5, Date.valueOf(fecha));
                statement.setString(6, genero);
                statement.setString(7, direccion);
                statement.setString(8, telefono);
                statement.setString(9, ciudad);
                statement.setString(10, civil);
                statement.setString(11, correo);
                statement.setString(12, estado);
                statement.setInt(13, nitIps);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Afiliado agregado correctamente.");
            }

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Error al agregar el afiliado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new IngresarAfiGUI();
    }
}
