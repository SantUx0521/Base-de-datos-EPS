package Contenido.Bancos;

import Contenido.Bancos.IngresarPago;
import Contenido.Bancos.GenerarReportePago;
import Contenido.Bancos.IngresarPagoBloque;
import Contenido.Principal.Main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class BancoGui extends JFrame {
    private JButton btnIngresarPago, btnGenerarReporte, btnCargarPagos, btnCerrarSesion;
    private JLabel lblTitle;

    public BancoGui() {
        setTitle("Gestor Bancario");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 10));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblTitle = new JLabel("Bienvenido al gestor bancario!!", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(new Color(0, 150, 136));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        Font font = new Font("Arial", Font.PLAIN, 16);

        btnIngresarPago = new JButton("Registrar pagos manualmente");
        btnIngresarPago.setFont(font);
        btnIngresarPago.setBackground(new Color(0, 150, 136));
        btnIngresarPago.setForeground(Color.WHITE);
        btnIngresarPago.setFocusPainted(false);
        btnIngresarPago.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnIngresarPago.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnGenerarReporte = new JButton("Generar reportes de pagos");
        btnGenerarReporte.setFont(font);
        btnGenerarReporte.setBackground(new Color(255, 87, 34));
        btnGenerarReporte.setForeground(Color.WHITE);
        btnGenerarReporte.setFocusPainted(false);
        btnGenerarReporte.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnGenerarReporte.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnCargarPagos = new JButton("Cargar pagos desde archivo");
        btnCargarPagos.setFont(font);
        btnCargarPagos.setBackground(new Color(33, 150, 243));
        btnCargarPagos.setForeground(Color.WHITE);
        btnCargarPagos.setFocusPainted(false);
        btnCargarPagos.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnCargarPagos.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setFont(font);
        btnCerrarSesion.setBackground(new Color(255, 82, 82));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Acciones para cada botón
        btnIngresarPago.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IngresarPago pago = new IngresarPago();
                pago.ingresarPago(null);
            }
        });

        btnGenerarReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenerarReportePago reporte = new GenerarReportePago();
                reporte.generarReporteGrafico(null);
            }
        });

        btnCargarPagos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IngresarPagoBloque pagoBloque = new IngresarPagoBloque();
                pagoBloque.cargarPagosDesdeArchivo(null);
            }
        });

        // Acción para el botón de Cerrar sesión
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Cierra la ventana actual (BancoGui)
                new Main().main(null);  // Llama al método main para abrir la ventana de login nuevamente
            }
        });

        // Agregar los botones al panel
        buttonPanel.add(btnIngresarPago);
        buttonPanel.add(Box.createVerticalStrut(20));  // Espaciado
        buttonPanel.add(btnGenerarReporte);
        buttonPanel.add(Box.createVerticalStrut(20));  // Espaciado
        buttonPanel.add(btnCargarPagos);
        buttonPanel.add(Box.createVerticalStrut(20));  // Espaciado
        buttonPanel.add(btnCerrarSesion);  // Añadir el botón de Cerrar sesión

        mainPanel.add(lblTitle, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BancoGui().setVisible(true);
            }
        });
    }
}
