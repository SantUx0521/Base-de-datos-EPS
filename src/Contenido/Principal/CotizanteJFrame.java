package Contenido.Principal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Contenido.ConectividadSQL;
class CotizanteJFrame extends JPanel {

    private String username;

    public CotizanteJFrame(String username) {
        this.username = username;
        initComponents();
        loadUserData();
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel_Title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userInfoTextArea = new javax.swing.JTextArea();
        backButton = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel_Title.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel_Title.setText("Información del Cotizante");

        userInfoTextArea.setEditable(false);
        userInfoTextArea.setColumns(20);
        userInfoTextArea.setRows(5);
        jScrollPane1.setViewportView(userInfoTextArea);

        backButton.setBackground(new java.awt.Color(204, 204, 255));
        backButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        backButton.setText("Volver");
        backButton.setBorder(null);
        backButton.addActionListener(evt -> backButtonActionPerformed());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_Title)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel_Title)
                    .addGap(20, 20, 20)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    private void loadUserData() {
        try (Connection connection = ConectividadSQL.obtenerConexion()) {
            if (connection != null) {
                String query = "SELECT * FROM cotizantes WHERE username = ?";
                try (PreparedStatement stmt = connection.prepareStatement(query)) {
                    stmt.setString(1, username);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            String numDoc = rs.getString("num_doc");
                            String tipoDoc = rs.getString("tipo_doc");
                            String fechaAfil = rs.getString("fecha_afil");
                            String salario = rs.getString("salario");

                            userInfoTextArea.setText(
                                "Número de Documento: " + numDoc + "\n" +
                                "Tipo de Documento: " + tipoDoc + "\n" +
                                "Fecha de Afiliación: " + fechaAfil + "\n" +
                                "Salario: " + salario
                            );
                        } else {
                            userInfoTextArea.setText("No se encontró información para este usuario.");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            userInfoTextArea.setText("Error al cargar los datos del usuario.");
        }
    }

    private void backButtonActionPerformed() {
        JFrame cotizantesFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        cotizantesFrame.dispose();

        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.add(new frmlogin());
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    private javax.swing.JButton backButton;
    private javax.swing.JLabel jLabel_Title;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea userInfoTextArea;
}
