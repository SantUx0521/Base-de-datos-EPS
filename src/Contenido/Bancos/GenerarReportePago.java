package Contenido.Bancos;

import Contenido.ConectividadSQL;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenerarReportePago {
    private Connection connection;

    public GenerarReportePago() {
        connection = ConectividadSQL.obtenerConexion();  
        if (connection == null) {
            System.out.println("Error: No se pudo establecer la conexión con la base de datos.");
        }
    }
    // Método para listar pagos por afiliado y rango de fechas
    public List<Pago> listarPagos(int numeroDocumentoAfiliado, Date fechaInicio, Date fechaFin) {
        List<Pago> pagos = new ArrayList<>();
        String query = "SELECT PA.Codigo_Pago, A.Num_Doc AS Numero_Documento_Afiliado, A.Nombres || ' ' || A.Apellidos AS Nombre_Completo, " +
                       "C.Num_Radicado, PA.Fecha_pago, PA.Valor " +
                       "FROM PAGO_APORTE PA " +
                       "INNER JOIN CONTRATO C ON PA.Num_Rad = C.Num_Radicado " +
                       "INNER JOIN Cotizante CO ON C.Cot_Num = CO.Num_Doc " +
                       "INNER JOIN Afiliado A ON CO.Num_Doc = A.Num_Doc " +
                       "WHERE A.Num_Doc = ? AND PA.Fecha_pago BETWEEN ? AND ? " +
                       "ORDER BY PA.Fecha_pago;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, numeroDocumentoAfiliado);
            statement.setDate(2, fechaInicio);
            statement.setDate(3, fechaFin);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int codigoPago = rs.getInt("Codigo_Pago");
                    int numDocAfiliado = rs.getInt("Numero_Documento_Afiliado");
                    String nombreCompleto = rs.getString("Nombre_Completo");
                    int numRadicado = rs.getInt("Num_Radicado");
                    Date fechaPago = rs.getDate("Fecha_pago");
                    double valor = rs.getDouble("Valor");

                    pagos.add(new Pago(codigoPago, numDocAfiliado, fechaPago, valor));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar los pagos: " + e.getMessage());
        }

        return pagos;
    }

    // Método para generar la interfaz gráfica y mostrar los pagos en una tabla
    public void generarReporteGrafico() {
        // Crear ventana principal
        JFrame frame = new JFrame("Reporte de Pagos");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Panel de contenido
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245)); // Fondo claro

        // Crear tabla para mostrar los datos
        String[] columns = {"Código de Pago", "Número de Radicado", "Nombre Afiliado", "Fecha de Pago", "Valor"};
        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        table.setModel(tableModel);
        
        // Crear scroll para la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Filtros de búsqueda
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        JLabel lblDocumento = new JLabel("Número de Documento:");
        JTextField txtDocumento = new JTextField(10);
        JLabel lblFechaInicio = new JLabel("Fecha Inicio (YYYY-MM-DD):");
        JTextField txtFechaInicio = new JTextField(10);
        JLabel lblFechaFin = new JLabel("Fecha Fin (YYYY-MM-DD):");
        JTextField txtFechaFin = new JTextField(10);
        JButton btnGenerar = new JButton("Generar Reporte");
        inputPanel.add(lblDocumento);
        inputPanel.add(txtDocumento);
        inputPanel.add(lblFechaInicio);
        inputPanel.add(txtFechaInicio);
        inputPanel.add(lblFechaFin);
        inputPanel.add(txtFechaFin);
        inputPanel.add(btnGenerar);
        
        panel.add(inputPanel, BorderLayout.NORTH);

        // Acción del botón "Generar Reporte"
        btnGenerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numDoc = Integer.parseInt(txtDocumento.getText());
                    Date fechaInicio = Date.valueOf(txtFechaInicio.getText());
                    Date fechaFin = Date.valueOf(txtFechaFin.getText());

                    // Llamar al método listarPagos y obtener los pagos
                    List<Pago> pagos = listarPagos(numDoc, fechaInicio, fechaFin);

                    // Limpiar la tabla antes de mostrar nuevos datos
                    tableModel.setRowCount(0);

                    // Agregar los pagos a la tabla
                    for (Pago pago : pagos) {
                        tableModel.addRow(new Object[]{
                            pago.getCodigoPago(),
                            pago.getNumRadicado(),
                            pago.getFechaPago(),
                            pago.getValor()
                        });
                    }
                    if (pagos.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "No se encontraron pagos para el rango especificado.");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese un número de documento válido.");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, "Formato de fecha inválido. Use el formato (YYYY-MM-DD).");
                }
            }
        });
        frame.add(panel);
        frame.setVisible(true);
    }
}
