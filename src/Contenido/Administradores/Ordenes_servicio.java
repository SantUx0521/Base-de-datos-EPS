package Contenido.Administradores;
import java.sql.Date;

public class Ordenes_servicio{
    private int cod_orden;
    private Date fecha;
    private int doc_paciente;
    private String medico;
    private String diagnostico;
    
    public Ordenes_servicio(int cod_orden, Date fecha, int doc_paciente, String medico, String diagnostico){
        this.cod_orden = cod_orden;
        this.fecha = fecha;
        this.doc_paciente = doc_paciente;
        this.medico = medico;
        this.diagnostico = diagnostico;
    }

    public int getCod_orden() {
        return cod_orden;
    }

    public void setCod_orden(int cod_orden) {
        this.cod_orden = cod_orden;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDoc_paciente() {
        return doc_paciente;
    }

    public void setDoc_paciente(int doc_paciente) {
        this.doc_paciente = doc_paciente;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
}