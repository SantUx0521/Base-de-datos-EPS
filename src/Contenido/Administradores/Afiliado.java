package Contenido.Administradores;
// importante al momento de crear  las clases de las entidades, que contengan todos los atributos en una estructura similar a la de la base de datos original, para evitar problemas
public class Afiliado {
    private Integer num_doc;
    private String tipo_doc;
    private String apellidos;
    private String nombres;
    private String fecha_nac;
    private String genero;
    private String direccion;
    private String telefono;
    private String ciudad;
    private String estado_civil;
    private String correo;
    private String estado;
    private int nit_ips;
    
    public Afiliado(Integer num_doc, String tipo_doc, String apellidos, String nombres, String fecha_nac, String genero, 
                    String direccion, String telefono, String ciudad, String estado_civil, String correo, String estado, int nit_ips) {
        this.num_doc = num_doc;
        this.tipo_doc = tipo_doc;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.fecha_nac = fecha_nac;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.estado_civil = estado_civil;
        this.correo = correo;
        this.estado = estado;
        this.nit_ips = nit_ips;
    }

    public Integer getNum_doc() {
        return num_doc;
    }

    public void setNum_doc(Integer num_doc) {
        this.num_doc = num_doc;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNit_ips() {
        return nit_ips;
    }

    public void setNit_ips(int nit_ips) {
        this.nit_ips = nit_ips;
    }
}
