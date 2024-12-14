package Contenido.Administradores;

public class Empresa {
    private int nit;
    private String razonSocial;
    private String ciudad;
    private String direccion;
    private String nombreContacto;

    public Empresa(int nit, String razonSocial, String ciudad, String direccion, String nombreContacto) {
        this.nit = nit;
        this.razonSocial = razonSocial;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.nombreContacto = nombreContacto;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }
}
