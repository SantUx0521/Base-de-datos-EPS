package Contenido;

import Contenido.Administradores.IngresarOrden;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdenesServAdminGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OrdenesServAdminGUI().crearVentanaPrincipal();
            }
        });
    }

    // Método que crea la ventana principal
    public void crearVentanaPrincipal() {
        // Crear la ventana principal
        JFrame frame = new JFrame("Administración de Órdenes de Servicio");
        frame.setSize(500, 300); // Ajuste de tamaño mayor para más espacio
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Deshabilitar la opción de cerrar con la X
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Crear un panel principal con margen
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes alrededor del panel

        // Título con color azul
        JLabel titleLabel = new JLabel("Administración de Órdenes de Servicio", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 123, 255)); // Azul
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10)); // Distribución de botones con espacio

        // Crear los botones
        JButton btnIngresarOrden = new JButton("Ingresar Orden de Servicio");
        JButton btnModificarOrden = new JButton("Modificar Orden de Servicio");
        JButton btnVolver = new JButton("Volver");

        // Estilo de los botones
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        Dimension buttonSize = new Dimension(300, 40); // Tamaño de los botones
        btnIngresarOrden.setFont(buttonFont);
        btnIngresarOrden.setPreferredSize(buttonSize);
        btnModificarOrden.setFont(buttonFont);
        btnModificarOrden.setPreferredSize(buttonSize);
        btnVolver.setFont(buttonFont);
        btnVolver.setPreferredSize(buttonSize);

        // Alineación de los botones
        btnIngresarOrden.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnModificarOrden.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agregar botones al panel
        buttonPanel.add(btnIngresarOrden);
        buttonPanel.add(btnModificarOrden);
        buttonPanel.add(btnVolver);

        // Añadir el panel de botones al panel principal
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        frame.add(mainPanel);

        // Eventos para los botones
        btnIngresarOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la ventana de IngresarOrden
                IngresarOrden ingresarOrden = new IngresarOrden();
                ingresarOrden.GenerarOrden();  // Método que abre la ventana de ingreso
            }
        });

        btnModificarOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la ventana de ModificarOrdenGUI
                ModificarOrdenGUI modificarOrden = new ModificarOrdenGUI();
                modificarOrden.setVisible(true);  // Método que abre la ventana de modificación
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual y volver al panel de administración principal
                frame.dispose();
                SwingUtilities.invokeLater(() -> new AdministradorFrame().createAndShowGUI());
            }
        });

        // Hacer visible la ventana
        frame.setVisible(true);
    }
}
