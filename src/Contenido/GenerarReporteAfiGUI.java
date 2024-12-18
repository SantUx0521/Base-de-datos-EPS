package Contenido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import Contenido.ConectividadSQL;

public class GenerarReporteAfiGUI extends JFrame {
    private Connection connection;
    private JButton btnActivos, btnInactivos, btnSalir;
    private JTextArea textAreaReporte;
    private JScrollPane scrollPane;

    public GenerarReporteAfiGUI() {
        connection = ConectividadSQL.obtenerConexion();
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("Error en la conexión a la base de datos.");
        }

        // Configuración de la ventana
        setTitle("Generar Reporte de Afiliados");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana
        setResizable(false); // No se puede redimensionar la ventana

        // Panel principal con BorderLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes alrededor

        // Estilo de los componentes
        Font font = new Font("Arial", Font.PLAIN, 14);

        // Panel de botones (superior)
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 3, 10, 10)); // 1 fila, 3 columnas, con espacio entre botones

        btnActivos = new JButton("Listado Activos");
        btnActivos.setFont(font);
        btnActivos.setBackground(new Color(0, 150, 136)); // Color verde suave
        btnActivos.setForeground(Color.WHITE);
        btnActivos.setFocusPainted(false);
        btnActivos.setBorderPainted(false);

        btnInactivos = new JButton("Listado Inactivos");
        btnInactivos.setFont(font);
        btnInactivos.setBackground(new Color(255, 87, 34)); // Color rojo suave
        btnInactivos.setForeground(Color.WHITE);
        btnInactivos.setFocusPainted(false);
        btnInactivos.setBorderPainted(false);

        btnSalir = new JButton("Salir");
        btnSalir.setFont(font);
        btnSalir.setBackground(new Color(233, 30, 99)); // Color rosa suave
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFocusPainted(false);
        btnSalir.setBorderPainted(false);

        panelBotones.add(btnActivos);
        panelBotones.add(btnInactivos);
        panelBotones.add(btnSalir);

        // Área de texto para mostrar el reporte
        textAreaReporte = new JTextArea();
        textAreaReporte.setFont(font);
        textAreaReporte.setEditable(false); // No editable
        textAreaReporte.setLineWrap(true);
        textAreaReporte.setWrapStyleWord(true);
        scrollPane = new JScrollPane(textAreaReporte);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Añadir los componentes al panel principal
        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Añadir panel al JFrame
        add(panel);

        // Eventos
        btnActivos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporte("Activo");
            }
        });

        btnInactivos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporte("Inactivo");
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cerrar la aplicación
            }
        });
    }

    private void generarReporte(String estado) {
        String query = "SELECT estado, nombres FROM afiliado WHERE estado = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, estado);
            try (ResultSet resultSet = statement.executeQuery()) {
                textAreaReporte.setText(""); // Limpiar área de texto antes de mostrar el reporte
                textAreaReporte.append("Reporte de Afiliados - Estado: " + estado + "\n");
                textAreaReporte.append("Estado | Nombre\n");
                textAreaReporte.append("--------------------------\n");

                while (resultSet.next()) {
                    String nombre = resultSet.getString("nombres");
                    textAreaReporte.append(estado + " | " + nombre + "\n");
                }
            }
        } catch (SQLException e) {
            textAreaReporte.setText("Error al generar el reporte: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GenerarReporteAfiGUI().setVisible(true);
            }
        });
    }
}
