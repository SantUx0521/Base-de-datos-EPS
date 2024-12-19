package Contenido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionAfiliadosAdminGUI extends JFrame {
    private JButton btnGenerarReporte, btnIngresarAfiliado, btnModificarAfiliado, btnVolver;

    public GestionAfiliadosAdminGUI() {
        // Configuración de la ventana
        setTitle("Gestión de Afiliados - Administrador");
        setSize(500, 400); // Aumenta el tamaño de la ventana
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Deshabilitar la opción de cerrar con la X
        setLocationRelativeTo(null); // Centra la ventana
        setResizable(false); // No permite redimensionar la ventana

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Texto de bienvenida
        JLabel lblBienvenida = new JLabel("Gestión de Afiliados", SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 16));
        lblBienvenida.setForeground(new Color(0, 123, 255)); // Color azul

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1, 10, 10));

        // Crear botones
        btnGenerarReporte = new JButton("Generar Reporte de Afiliados");
        btnIngresarAfiliado = new JButton("Ingresar Nuevo Afiliado");
        btnModificarAfiliado = new JButton("Modificar Afiliado");
        btnVolver = new JButton("Volver");

        // Estilo de los botones
        Font font = new Font("Arial", Font.BOLD, 14);
        btnGenerarReporte.setFont(font);
        btnIngresarAfiliado.setFont(font);
        btnModificarAfiliado.setFont(font);
        btnVolver.setFont(font);

        // Ajustar el tamaño de los botones
        Dimension buttonSize = new Dimension(300, 40);
        btnGenerarReporte.setPreferredSize(buttonSize);
        btnIngresarAfiliado.setPreferredSize(buttonSize);
        btnModificarAfiliado.setPreferredSize(buttonSize);
        btnVolver.setPreferredSize(buttonSize);

        // Alineación de los botones
        btnGenerarReporte.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIngresarAfiliado.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnModificarAfiliado.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agregar botones al panel de botones
        panelBotones.add(btnGenerarReporte);
        panelBotones.add(btnIngresarAfiliado);
        panelBotones.add(btnModificarAfiliado);
        panelBotones.add(btnVolver);

        // Agregar componentes al panel principal
        panel.add(lblBienvenida, BorderLayout.NORTH);
        panel.add(panelBotones, BorderLayout.CENTER);

        // Agregar el panel a la ventana
        add(panel);

        // Agregar acciones a los botones
        btnGenerarReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GenerarReporteAfiGUI().setVisible(true);
            }
        });

        btnIngresarAfiliado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new IngresarAfiGUI();
            }
        });

        btnModificarAfiliado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModificarAfiGUI().setVisible(true);
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual y volver al panel de administración principal
                dispose();
                SwingUtilities.invokeLater(() -> new AdministradorFrame().createAndShowGUI());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestionAfiliadosAdminGUI().setVisible(true);
            }
        });
    }
}
