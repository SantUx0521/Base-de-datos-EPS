package Contenido.Administradores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Cotizante {
    private int num_doc; 
    private String tipo_doc; 
    private Date fecha_afil; 
    private int salario; 

    public Cotizante(int num_doc, String tipo_doc,Date fecha_afil, int salario){
        this.num_doc = num_doc;
        this.tipo_doc = tipo_doc;
        this.fecha_afil = fecha_afil;
        this.salario = salario;
    }

    public int getNum_doc() {
        return num_doc;
    }

    public void setNum_doc(int num_doc) {
        this.num_doc = num_doc;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public Date getFecha_afil() {
        return fecha_afil;
    }

    public void setFecha_afil(Date fecha_afil) {
        this.fecha_afil = fecha_afil;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }
}
