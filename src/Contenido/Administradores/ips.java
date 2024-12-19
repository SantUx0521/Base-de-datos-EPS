package Contenido.Administradores;

public class ips {
    private static int nit;
        private String razonSocial;
        private String nivelAtencion;
    
        public ips(int nit, String razonSocial, String nivelAtencion) {
            this.nit = nit;
            this.razonSocial = razonSocial;
            this.nivelAtencion = nivelAtencion;
        }
    
    public static int getNit() {
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

    public String getNivelAtencion() {
        return nivelAtencion;
    }

    public void setNivelAtencion(String nivelAtencion) {
        this.nivelAtencion = nivelAtencion;
    }
}