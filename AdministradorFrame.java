package Contenido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministradorFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdministradorFrame().createAndShowGUI());
    }

    public void createAndShowGUI() {
        // Crear la ventana principal
        JFrame frame = new JFrame("Menú de Administración - Panel de Control");
        frame.setSize(500, 400); // Tamaño más grande
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false); // No se puede redimensionar la ventana

        // Configuración del panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panel.setBackground(new Color(240, 240, 240)); // Color de fondo más claro

        // Título de la ventana
        JLabel titleLabel = new JLabel("Panel de Administración");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(70, 130, 180)); // Color de texto azul

        // Crear botones de navegación
        JButton btnContratos = new JButton("Gestión de Contratos");
        JButton btnEmpresas = new JButton("Gestión de Empresas");
        JButton btnAfiliados = new JButton("Gestión de Afiliados");
        JButton btnIps = new JButton("Administración de IPS");
        JButton btnOrdenes = new JButton("Órdenes de Servicio");
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");

        // Estilo de los botones
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Dimension buttonSize = new Dimension(300, 40); // Todos los botones tendrán el mismo tamaño
        btnContratos.setFont(buttonFont);
        btnContratos.setPreferredSize(buttonSize);

        btnEmpresas.setFont(buttonFont);
        btnEmpresas.setPreferredSize(buttonSize);

        btnAfiliados.setFont(buttonFont);
        btnAfiliados.setPreferredSize(buttonSize);

        btnIps.setFont(buttonFont);
        btnIps.setPreferredSize(buttonSize);

        btnOrdenes.setFont(buttonFont);
        btnOrdenes.setPreferredSize(buttonSize);

        btnCerrarSesion.setFont(buttonFont);
        btnCerrarSesion.setPreferredSize(buttonSize);

        // Alineación de los botones
        btnContratos.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEmpresas.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAfiliados.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIps.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOrdenes.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agregar los elementos al panel
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnContratos);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnEmpresas);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnAfiliados);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnIps);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnOrdenes);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnCerrarSesion);

        // Acciones de los botones
        btnContratos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new ContratosAdminGUI().createAndShowGUI());
            }
        });

        btnEmpresas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new EmpresasAdminGUI().setVisible(true));
            }
        });

        btnAfiliados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new GestionAfiliadosAdminGUI().setVisible(true));
            }
        });

        btnIps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new IpsAdminGUI().setVisible(true));
            }
        });

        btnOrdenes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new OrdenesServAdminGUI().crearVentanaPrincipal());
            }
        });

        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Salir de la aplicación
            }
        });

        // Agregar el panel al JFrame
        frame.add(panel);
        frame.setVisible(true);
    }
}
