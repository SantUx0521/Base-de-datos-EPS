package Contenido.Administradores;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenerarReportePago {
    private Connection connection;

    public GenerarReportePago(Connection connection) {
        this.connection = connection;
    }

    public List<Pago> listarPagosPorAfiliadoYRangoFechas(int numeroDocumentoAfiliado, Date fechaInicio, Date fechaFin) {
        List<Pago> pagos = new ArrayList<>();
        String query = "SELECT PA.Codigo_Pago, A.Num_Doc AS Numero_Documento_Afiliado, A.Nombres || ' ' || A.Apellidos AS Nombre_Completo, C.Num_Radicado, PA.Fecha_pago, PA.Valor FROM PAGO_APORTE PA INNER JOIN CONTRATO C ON PA.Num_Rad = C.Num_Radicado INNER JOIN Cotizante CO ON C.Cot_Num = CO.Num_Doc INNER JOIN Afiliado A ON CO.Num_Doc = A.Num_Doc WHERE A.Num_Doc = ? AND PA.Fecha_pago BETWEEN ? AND ? ORDER BY PA.Fecha_pago;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, numeroDocumentoAfiliado);
            statement.setDate(2, fechaInicio);
            statement.setDate(3, fechaFin);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Pago pago = new Pago(
                        rs.getInt("Codigo_Pago"),
                        rs.getInt("Numero_Documento_Afiliado"),
                        rs.getString("Nombre_Completo"),
                        rs.getInt("Num_Radicado"),
                        rs.getDate("Fecha_pago"),
                        rs.getDouble("Valor")
                    );
                    pagos.add(pago);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los pagos: " + e.getMessage());
        }

        return pagos;
    }
}


