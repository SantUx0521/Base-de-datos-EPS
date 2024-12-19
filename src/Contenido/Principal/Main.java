package Contenido.Principal;

import javax.swing.*;

import Contenido.ConectividadSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class to run the application
 */
public class Main {

    public static void main(String[] args) {
        try {
            Connection connection = ConectividadSQL.obtenerConexion();
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos!");

                // Ejecutar una consulta simple
                String query = "SELECT version()";
                java.sql.Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                // Procesar los resultados
                if (rs.next()) {
                    System.out.println("Versión de PostgreSQL: " + rs.getString(1));
                }

                // Cerrar la conexión
                rs.close();
                stmt.close();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar con la base de datos.");
        }

        // Create the main frame
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the login panel
        frmlogin loginPanel = new frmlogin();

        // Add the panel to the frame
        frame.add(loginPanel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }
}

class frmlogin extends JPanel {

    //public static frmCajero frmc;
    //private JTextField txtUsername;
    //private JPasswordField txtPassword;

    // Map to store username, password, and role
    private Map<String, String[]> userDatabase;

    public frmlogin() {
        initComponents();
        initializeUserDatabase();
    }

    // Initialize user database with some example users
    private void initializeUserDatabase() {
            userDatabase = new HashMap<>();
            userDatabase.put("admin1", new String[]{"password1", "Administrador"});
            userDatabase.put("banco1", new String[]{"password2", "Bancos"});
            userDatabase.put("cotizante1", new String[]{"password3", "Cotizantes"});
            // Agregar más usuarios según sea necesario
    }

    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
   // ...existing code...
private void initComponents() {
    jPanel1 = new javax.swing.JPanel();
    jLabel4 = new javax.swing.JLabel();
    jLabel_IniciarSesion = new javax.swing.JLabel();
    jLabel_LogoHotel = new javax.swing.JLabel();
    jLabel_Contraseña = new javax.swing.JLabel();
    jLabel_Usuario = new javax.swing.JLabel();
    txtUsername = new javax.swing.JTextField();
    Button_Ingresar = new javax.swing.JButton();
    txtPassword = new javax.swing.JPasswordField();
    jPanel1.setBackground(new java.awt.Color(255, 255, 255));
    jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Contenido/Principal/Imagene/immg1.pngw"))); // NOI18N
    jLabel_IniciarSesion.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
    jLabel_IniciarSesion.setText("INICIAR SESION");
    jLabel_IniciarSesion.setToolTipText("");
    jLabel_LogoHotel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
    jLabel_LogoHotel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Contenido/Principal/Imagene/favicon.png"))); // NOI18N
    jLabel_LogoHotel.setText("EPS Esperar para salvarse");
    jLabel_LogoHotel.setToolTipText("");
    jLabel_Contraseña.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
    jLabel_Contraseña.setText("CONTRASEÑA");
    jLabel_Usuario.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
    jLabel_Usuario.setText("USUARIO");
    txtUsername.setForeground(new java.awt.Color(102, 102, 102));
    txtUsername.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
    txtUsername.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtUsernameActionPerformed(evt);
        }
    });
    Button_Ingresar.setBackground(new java.awt.Color(204, 204, 255));
    Button_Ingresar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
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
                .addComponent(jLabel_LogoHotel)
                .addComponent(jLabel_IniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel_Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(Button_Ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtUsername)
                .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
            .addGap(123, 123, 123)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(30, 30, 30)
            .addComponent(jLabel_LogoHotel)
            .addGap(53, 53, 53)
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
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
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
// ...existing code...
    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
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
                    JFrame administradorFrame = new JFrame("Administrador");
                    administradorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //administradorFrame.add(new frmAdministrador());
                    administradorFrame.pack();
                    administradorFrame.setLocationRelativeTo(null);
                    administradorFrame.setVisible(true);
                } else if ("Bancos".equals(role)) {
                    JFrame bancosFrame = new JFrame("Bancos");
                    bancosFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //bancosFrame.add(new frmBancos());
                    bancosFrame.pack();
                    bancosFrame.setLocationRelativeTo(null);
                    bancosFrame.setVisible(true);
                } else if ("Cotizantes".equals(role)) {
                    JFrame cotizantesFrame = new JFrame("Cotizantes");
                    cotizantesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //cotizantesFrame.add(new frmCotizantes());
 
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

    // Variables declaration - do not modify                     
    private javax.swing.JButton Button_Ingresar;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_Contraseña;
    private javax.swing.JLabel jLabel_IniciarSesion;
    private javax.swing.JLabel jLabel_LogoHotel;
    private javax.swing.JLabel jLabel_Usuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration                   
}
