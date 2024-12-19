package Contenido;

import Contenido.Administradores.IngresarIPS;
import Contenido.Administradores.ListadoCitas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IpsAdminGUI extends JFrame {

    public IpsAdminGUI() {
        // Configuración de la ventana
        setTitle("Administración de IPS");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Deshabilita la opción de cerrar con la X
        setLocationRelativeTo(null); // Centra la ventana
        setResizable(false); // No permite redimensionar la ventana

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10)); // Se ajusta a 5 filas para incluir el nuevo botón
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titleLabel = new JLabel("Gestión de IPS", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 123, 255)); // Color azul
        panel.add(titleLabel);

        // Botón para Modificar IPS
        JButton btnModificarIps = new JButton("Modificar IPS");
        btnModificarIps.setFont(new Font("Arial", Font.PLAIN, 14));
        btnModificarIps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new ModificarIpsGUI().setVisible(true);
                });
            }
        });

        // Botón para Ingresar IPS
        JButton btnIngresarIps = new JButton("Registrar IPS");
        btnIngresarIps.setFont(new Font("Arial", Font.PLAIN, 14));
        btnIngresarIps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    IngresarIPS ingresarIPS = new IngresarIPS();
                    ingresarIPS.agregarIps();
                });
            }
        });

        // Botón para Listar Citas
        JButton btnListarCitas = new JButton("Listado de Citas");
        btnListarCitas.setFont(new Font("Arial", Font.PLAIN, 14));
        btnListarCitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    ListadoCitas listadoCitas = new ListadoCitas();
                    listadoCitas.listar();
                });
            }
        });

        // Botón para Volver al Panel de Administración
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.PLAIN, 14));
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
                SwingUtilities.invokeLater(() -> new AdministradorFrame().createAndShowGUI()); // Regresa al panel principal
            }
        });

        // Añadir los botones al panel
        panel.add(btnModificarIps);
        panel.add(btnIngresarIps);
        panel.add(btnListarCitas);
        panel.add(btnVolver);

        // Añadir panel al JFrame
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new IpsAdminGUI().setVisible(true);
        });
    }
}
