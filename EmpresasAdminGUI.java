package Contenido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmpresasAdminGUI extends JFrame {

    public EmpresasAdminGUI() {
        // Configuración de la ventana principal
        setTitle("Gestión de Empresas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Deshabilitar la opción de cerrar con la X
        setLocationRelativeTo(null); // Centra la ventana
        

        // Crear un panel principal con margen
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Título con color azul
        JLabel titleLabel = new JLabel("Gestión de Empresas", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(70, 130, 180)); // Azul
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnIngresar = new JButton("Ingresar Empresa");
        JButton btnModificar = new JButton("Modificar Empresa");
        JButton btnVolver = new JButton("Volver");

        // Estilo de los botones
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        Dimension buttonSize = new Dimension(300, 40);
        btnIngresar.setFont(buttonFont);
        btnIngresar.setPreferredSize(buttonSize);
        btnModificar.setFont(buttonFont);
        btnModificar.setPreferredSize(buttonSize);
        btnVolver.setFont(buttonFont);
        btnVolver.setPreferredSize(buttonSize);

        // Alineación de los botones
        btnIngresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnModificar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agregar botones al panel
        buttonPanel.add(btnIngresar);
        buttonPanel.add(btnModificar);
        buttonPanel.add(btnVolver);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);

        // Eventos para los botones
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new IngresarEmpGUI().setVisible(true);
                dispose();
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModificarEmpGUI().setVisible(true);
                dispose();
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
        SwingUtilities.invokeLater(() -> {
            new EmpresasAdminGUI().setVisible(true);
        });
    }
}
