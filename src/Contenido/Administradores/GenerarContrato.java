package Contenido.Administradores;

import Contenido.ConectividadSQL;
import Contenido.Administradores.Contrato;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class GenerarContrato {
    private Connection connection;

    public GenerarContrato() {
        connection = ConectividadSQL.obtenerConexion();  
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("Error en la conexión a la base de datos.");
        }
    }

    public void IngresarContrato() {
        JFrame frame = new JFrame("Registrar Contrato");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); 

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblNumRadicado = new JLabel("Número de Radicado:");
        JLabel lblCotNum = new JLabel("Número de Cotizante:");
        JLabel lblFechaRec = new JLabel("Fecha de Recepción:");
        JLabel lblSalarioBase = new JLabel("Salario Base:");
        JLabel lblEstado = new JLabel("Estado:");
        JLabel lblTipoContrato = new JLabel("Tipo de Contrato:");
        JLabel lblNitRut = new JLabel("NIT/RUT Empresa:");

        JTextField txtNumRadicado = new JTextField();
        JTextField txtCotNum = new JTextField();
        JTextField txtFechaRec = new JTextField(); 
        JTextField txtSalarioBase = new JTextField();
        JTextField txtEstado = new JTextField();
        JTextField txtTipoContrato = new JTextField();
        JTextField txtNitRut = new JTextField();

        JButton btnRegistrar = new JButton("Registrar Contrato");
        btnRegistrar.setBackground(new Color(102, 180, 255)); 
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setFocusPainted(false);

        panel.add(lblNumRadicado);
        panel.add(txtNumRadicado);
        panel.add(lblCotNum);
        panel.add(txtCotNum);
        panel.add(lblFechaRec);
        panel.add(txtFechaRec);
        panel.add(lblSalarioBase);
        panel.add(txtSalarioBase);
        panel.add(lblEstado);
        panel.add(txtEstado);
        panel.add(lblTipoContrato);
        panel.add(txtTipoContrato);
        panel.add(lblNitRut);
        panel.add(txtNitRut);
        panel.add(Box.createVerticalStrut(20)); 
        panel.add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {
            try {
                int numRadicado = Integer.parseInt(txtNumRadicado.getText());
                int cotNum = Integer.parseInt(txtCotNum.getText());
                Date fechaRec = Date.valueOf(txtFechaRec.getText()); 
                double salarioBase = Double.parseDouble(txtSalarioBase.getText());
                String estado = txtEstado.getText();
                String tipoContrato = txtTipoContrato.getText();
                
                int nitRut = Integer.parseInt(txtNitRut.getText());
        
                if (numRadicado == 0 || cotNum == 0 || fechaRec == null || salarioBase == 0 || estado.isEmpty() || tipoContrato.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.");
                } else {
                    Contrato nuevoContrato;
                    if (tipoContrato.equalsIgnoreCase("Dependiente")) {
                        nuevoContrato = new Contrato(numRadicado, cotNum, fechaRec, salarioBase, estado, tipoContrato, nitRut, 0); // rut = 0
                    } else if (tipoContrato.equalsIgnoreCase("Independiente")) {
                        nuevoContrato = new Contrato(numRadicado, cotNum, fechaRec, salarioBase, estado, tipoContrato, 0, nitRut); // nitEmpresa = 0
                    } else {
                        JOptionPane.showMessageDialog(frame, "Tipo de contrato no válido.");
                        return; 
                    }
        
                    boolean registrado = registrarContrato(nuevoContrato);
        
                    if (registrado) {
                        txtNumRadicado.setText("");
                        txtCotNum.setText("");
                        txtFechaRec.setText("");
                        txtSalarioBase.setText("");
                        txtEstado.setText("");
                        txtTipoContrato.setText("");
                        txtNitRut.setText("");
        
                        JOptionPane.showMessageDialog(frame, "Contrato registrado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "No se pudo registrar el contrato.");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error al registrar el contrato: " + ex.getMessage());
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    public boolean registrarContrato(Contrato contrato) {
        String query = "INSERT INTO contrato (num_radicado, cot_num, fecha_rec, salario_base, estado, tipo_cot, nit_empresa, rut) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, contrato.getNumRadicado());
            statement.setInt(2, contrato.getCotNum());
            statement.setDate(3, new java.sql.Date(contrato.getFechaRec().getTime()));  
            statement.setDouble(4, contrato.getSalarioBase());
            statement.setString(5, contrato.getEstado());
            statement.setString(6, contrato.getTipoContrato());

            if (contrato.getTipoContrato().equalsIgnoreCase("Dependiente")) { 
                statement.setInt(7, contrato.getNitEmpresa());
                statement.setNull(8, Types.INTEGER);  
            } else if (contrato.getTipoContrato().equalsIgnoreCase("Independiente")) { 
                statement.setNull(7, Types.INTEGER);  
                statement.setInt(8, contrato.getRut());
            }

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al registrar el contrato: " + e.getMessage());
            return false;
        }
    }
}
