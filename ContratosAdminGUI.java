package Contenido;

import Contenido.Administradores.GenerarContrato;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContratosAdminGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ContratosAdminGUI().createAndShowGUI());
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Gestión de Contratos - Administrador");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // No cerrar la ventana principal
        frame.setLocationRelativeTo(null);
        frame.setResizable(false); // No se puede redimensionar la ventana

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 240)); // Fondo claro para contraste

        // Título de la ventana
        JLabel titleLabel = new JLabel("Gestión de Contratos");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(70, 130, 180)); // Título en color azul

        // Botones
        JButton btnGenerarContrato = new JButton("Registrar Contrato");
        JButton btnModificarContrato = new JButton("Modificar Contrato");
        JButton btnVolver = new JButton("Volver");

        // Estilo de los botones
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        Dimension buttonSize = new Dimension(250, 40);

        btnGenerarContrato.setFont(buttonFont);
        btnGenerarContrato.setPreferredSize(buttonSize);

        btnModificarContrato.setFont(buttonFont);
        btnModificarContrato.setPreferredSize(buttonSize);

        btnVolver.setFont(buttonFont);
        btnVolver.setPreferredSize(buttonSize);


        // Alineación de los botones
        btnGenerarContrato.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnModificarContrato.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Acciones de los botones
        btnGenerarContrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenerarContrato generarContrato = new GenerarContrato();
                generarContrato.IngresarContrato();
            }
        });

        btnModificarContrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new ModificarContratoGUI().setVisible(true));
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cerrar la ventana actual
                new AdministradorFrame().createAndShowGUI(); // Volver al panel principal
            }
        });

        // Agregar los componentes al panel
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnGenerarContrato);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnModificarContrato);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnVolver);
        panel.add(Box.createVerticalStrut(10));

        // Agregar el panel al JFrame
        frame.add(panel);
        frame.setVisible(true);
    }
}