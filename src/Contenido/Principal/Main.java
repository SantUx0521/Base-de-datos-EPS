package Contenido.Principal;

import javax.swing.*;
import Contenido.ConectividadSQL;
import Contenido.Bancos.BancoGui;
import Contenido.Principal.CotizanteJFrame;
import Contenido.AdministradorFrame;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            Connection connection = ConectividadSQL.obtenerConexion();
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos!");
                String query = "SELECT version()";
                java.sql.Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                if (rs.next()) {
                    System.out.println("Versión de PostgreSQL: " + rs.getString(1));
                }

                rs.close();
                stmt.close();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar con la base de datos.");
        }

        // Crear la ventana de login
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmlogin loginPanel = new frmlogin();
        frame.add(loginPanel);
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
}

class frmlogin extends JPanel {

    private Map<String, String[]> userDatabase;

    public frmlogin() {
        initComponents();
        initializeUserDatabase();
    }

    private void initializeUserDatabase() {
        userDatabase = new HashMap<>();
        userDatabase.put("admin1", new String[]{"123", "Administrador"});
        userDatabase.put("banco1", new String[]{"123", "BancoGui"});
        userDatabase.put("cotizante1", new String[]{"123", "Cotizantes"});
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel_IniciarSesion = new javax.swing.JLabel();
        jLabel_Contraseña = new javax.swing.JLabel();
        jLabel_Usuario = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        Button_Ingresar = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel_IniciarSesion.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); 
        jLabel_IniciarSesion.setText("INICIAR SESION");

        jLabel_Contraseña.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        jLabel_Contraseña.setText("CONTRASEÑA");

        jLabel_Usuario.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        jLabel_Usuario.setText("USUARIO");

        txtUsername.setForeground(new java.awt.Color(102, 102, 102));
        txtUsername.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        Button_Ingresar.setBackground(new java.awt.Color(204, 204, 255));
        Button_Ingresar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        Button_Ingresar.setText("INGRESAR");
        Button_Ingresar.setBorder(null);
        Button_Ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_IngresarActionPerformed(evt);
            }
        });

        txtPassword.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_IniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_Ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
                .addGap(123, 123, 123))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel_IniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel_Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(Button_Ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }

    private void Button_IngresarActionPerformed(java.awt.event.ActionEvent evt) { 
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
    
        if (userDatabase.containsKey(username)) {
            String[] userDetails = userDatabase.get(username);
            String correctPassword = userDetails[0];
            String role = userDetails[1];
    
            if (correctPassword.equals(password)) {
                JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                loginFrame.dispose();
    
                if ("Administrador".equals(role)) {
                    AdministradorFrame adminFrame = new AdministradorFrame();
                    adminFrame.createAndShowGUI();
                } else if ("BancoGui".equals(role)) {
                    BancoGui bancoFrame = new BancoGui();
                    bancoFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Rol no reconocido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private javax.swing.JButton Button_Ingresar;
    private javax.swing.JLabel jLabel_Contraseña;
    private javax.swing.JLabel jLabel_IniciarSesion;
    private javax.swing.JLabel jLabel_Usuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
}
