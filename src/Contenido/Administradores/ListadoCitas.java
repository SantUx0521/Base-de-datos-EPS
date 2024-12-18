package Contenido.Administradores;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Contenido.Bancos.Pago;

public class ListadoCitas {
    private Connection connection;

    public ListadoCitas(Connection connection) {
        this.connection = connection;
    }
    public List<Pago> CitasIps(int CodIps, int codOrdenes){
        return null;
        
    }
}
