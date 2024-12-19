package Contenido.Administradores;

public class Servicios {
    private int codigoServicio;
    private int codOrden;
    private String descripcion;

    public Servicios(int codigoServicio, int codOrden, String descripcion) {
        this.codigoServicio = codigoServicio;
        this.codOrden = codOrden;
        this.descripcion = descripcion;
    }

    public int getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(int codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public int getCodOrden() {
        return codOrden;
    }

    public void setCodOrden(int codOrden) {
        this.codOrden = codOrden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Override
    public String toString() {
        return "Servicio{" +
                "codigoServicio=" + codigoServicio +
                ", codOrden=" + codOrden +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}