package Contenido.Administradores;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Contenido.Administradores.Ordenes_servicio;
import Contenido.Bancos.Pago;;

public class ListadoCitas {
    private Connection connection;

    public ListadoCitas(Connection connection) {
        this.connection = connection;
    }
    public boolean CitasIps(int CodIps, int codOrdenes, Date fechaInicio, Date fechaFin) {
        List<Ordenes_servicio> ordenes = new ArrayList<>();
        String query = "SELECT O.Codigo_Pago, A.Num_Doc, A.Nombre_Completo, O.Num_Radicado, O.Fecha_pago, O.Valor FROM ordenes O INNER JOIN afiliado A ON O.doc_paciente = A.num_doc INNER JOIN ips I ON A.nit_ips = I.nit WHERE I.nit = ? AND O.fecha BETWEEN ? AND ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, CodIps);
            statement.setDate(2, fechaInicio);
            statement.setDate(3, fechaFin);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Ordenes_servicio orden = new Ordenes_servicio(
                        rs.getInt("Codigo_Pago"),
                        rs.getDate("Fecha_pago"),
                        rs.getInt("Num_Doc"),
                        rs.getString("Medico"),
                        rs.getString("Diagnostico")
                    );
                    ordenes.add(orden);
                }
            }
    
        } catch (SQLException e) {
            System.out.println("Error al registrar la empresa: " + e.getMessage());
            return false;
        }
        return true; 
    }
}
