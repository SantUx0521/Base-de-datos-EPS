package Contenido.Administradores;

import Contenido.ConectividadSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.border.EmptyBorder;
import Contenido.Administradores.Ordenes_servicio;
import java.util.ArrayList;

public class ListadoCitas {

    private JFrame frame;
    private Connection connection;

    public ListadoCitas() {
        connection = ConectividadSQL.obtenerConexion();
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("Error en la conexión a la base de datos.");
        }

        Listar();
    }

    public void Listar() {
        frame = new JFrame("Listado de Citas por IPS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        frame.setLayout(new BorderLayout(10, 10));

        // Panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setBorder(new EmptyBorder(30, 30, 30, 30));
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        JLabel labelTitulo = new JLabel("Generar Reporte de Citas por IPS");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(labelTitulo);

        JTextField fieldCodIps = new JTextField();
        fieldCodIps.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldCodIps.setPreferredSize(new Dimension(200, 30));
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCentral.add(new JLabel("Código de IPS"));
        panelCentral.add(fieldCodIps);

        JTextField fieldCodOrdenes = new JTextField();
        fieldCodOrdenes.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldCodOrdenes.setPreferredSize(new Dimension(200, 30));
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCentral.add(new JLabel("Código de Orden"));
        panelCentral.add(fieldCodOrdenes);

        // Fechas
        JPanel panelFechas = new JPanel();
        panelFechas.setLayout(new FlowLayout(FlowLayout.LEFT));

        JTextField fieldFechaInicio = new JTextField();
        fieldFechaInicio.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldFechaInicio.setPreferredSize(new Dimension(120, 30));
        fieldFechaInicio.setText("Fecha Inicio (YYYY-MM-DD)");

        JTextField fieldFechaFin = new JTextField();
        fieldFechaFin.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldFechaFin.setPreferredSize(new Dimension(120, 30));
        fieldFechaFin.setText("Fecha Fin (YYYY-MM-DD)");

        panelFechas.add(new JLabel("Fecha de Inicio y Fin"));
        panelFechas.add(fieldFechaInicio);
        panelFechas.add(fieldFechaFin);
        panelCentral.add(panelFechas);

        // Botón para consultar
        JButton btnGenerarCitas = new JButton("Generar Reporte de Citas");
        btnGenerarCitas.setFont(new Font("Arial", Font.PLAIN, 14));
        btnGenerarCitas.setBackground(new Color(173, 216, 230)); // Azul claro
        btnGenerarCitas.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGenerarCitas.setFocusPainted(false);
        btnGenerarCitas.setBorderPainted(false);
        btnGenerarCitas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtención de las fechas
                    java.sql.Date fechaInicio = java.sql.Date.valueOf(fieldFechaInicio.getText());
                    java.sql.Date fechaFin = java.sql.Date.valueOf(fieldFechaFin.getText());
                    int codIps = Integer.parseInt(fieldCodIps.getText());
                    int codOrdenes = Integer.parseInt(fieldCodOrdenes.getText());

                    // Consulta de citas
                    CitasIps(codIps, codOrdenes, fechaInicio, fechaFin);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error al obtener los datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));
        panelCentral.add(btnGenerarCitas);

        frame.add(panelCentral, BorderLayout.CENTER);

        // Mostrar la ventana
        frame.setVisible(true);
    }

    public void CitasIps(int CodIps, int codOrdenes, java.sql.Date fechaInicio, java.sql.Date fechaFin) {
        ArrayList<Ordenes_servicio> ordenes = new ArrayList<>();
        
        // Consulta actualizada
        String query = "SELECT O.cod_orden, A.Num_Doc, A.Nombres || ' ' || A.Apellidos AS Nombre_Completo, " +
                       "O.Fecha, P.Fecha_pago, P.Valor " + 
                       "FROM ordenes O " +
                       "INNER JOIN afiliado A ON O.Doc_Paciente = A.Num_Doc " +
                       "INNER JOIN ips I ON A.Nit_ips = I.Nit " +
                       "INNER JOIN pago_aporte P ON O.Doc_Paciente = P.Num_Rad " +
                       "WHERE I.Nit = ? AND O.Fecha BETWEEN ? AND ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, CodIps);
            statement.setDate(2, fechaInicio);
            statement.setDate(3, fechaFin);
            
            try (ResultSet rs = statement.executeQuery()) {
                JFrame reporteFrame = new JFrame("Reporte de Citas");
                reporteFrame.setSize(600, 400);
                reporteFrame.setLocationRelativeTo(frame);
    
                JPanel panelResultados = new JPanel();
                panelResultados.setLayout(new BorderLayout());
                JTextArea textArea = new JTextArea();
                textArea.setEditable(false);
                textArea.setFont(new Font("Arial", Font.PLAIN, 14));
    
                // Agregar encabezado
                textArea.append("Código de Orden | Número de Documento | Nombre Completo | Fecha de Cita | Fecha de Pago | Valor\n");
                while (rs.next()) {
                    String codOrden = rs.getString("cod_orden");
                    String numDoc = rs.getString("Num_Doc");
                    String nombreCompleto = rs.getString("Nombre_Completo");  // Concatenación de nombre y apellido
                    String fechaCita = rs.getString("Fecha");
                    String fechaPago = rs.getString("Fecha_pago");
                    String valor = rs.getString("Valor");
                    
                    // Escribir en el área de texto
                    textArea.append(codOrden + " | " + numDoc + " | " + nombreCompleto + " | " + fechaCita + " | " + fechaPago + " | " + valor + "\n");
                }
    
                panelResultados.add(new JScrollPane(textArea), BorderLayout.CENTER);
                reporteFrame.add(panelResultados);
                reporteFrame.setVisible(true);
    
            }
    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error al obtener las citas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
