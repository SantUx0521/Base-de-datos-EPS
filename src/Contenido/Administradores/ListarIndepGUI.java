package Contenido.Administradores;

import Contenido.ConectividadSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ListarIndepGUI {

    private Connection connection;

    public ListarIndepGUI() {
        connection = ConectividadSQL.obtenerConexion();
        if (connection == null) {
            System.out.println("Error: No se pudo establecer la conexión con la base de datos.");
        }
    }

    public void mostrarAfiliadosIndependientes(JFrame ventanaAnterior) {
        JFrame frame = new JFrame("Listado de Afiliados Independientes por Estado");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        String[] columnas = {"ID Afiliado", "Nombre Completo", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(220, 53, 69));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.add(btnVolver);
        panel.add(footerPanel, BorderLayout.SOUTH);

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                if (ventanaAnterior != null) {
                    ventanaAnterior.setVisible(true);
                }
            }
        });

        cargarAfiliados(model);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void cargarAfiliados(DefaultTableModel model) {
        String query = "SELECT A.Num_Doc AS IdAfiliado, " +
                       "CONCAT(A.Nombres, ' ', A.Apellidos) AS NombreCompleto, " +
                       "A.Estado " +
                       "FROM Afiliado A " +
                       "JOIN Cotizante C ON A.Num_Doc = C.Num_Doc " +
                       "JOIN Contrato R ON C.Num_Doc = R.Cot_Num " +
                       "WHERE R.Tipo_Cot = 'Independiente' " +
                       "ORDER BY A.Estado, A.Nombres";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idAfiliado = resultSet.getInt("IdAfiliado");
                String nombreCompleto = resultSet.getString("NombreCompleto");
                String estado = resultSet.getString("Estado");
                model.addRow(new Object[]{idAfiliado, nombreCompleto, estado});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los afiliados: " + e.getMessage());
        }
    }
}

