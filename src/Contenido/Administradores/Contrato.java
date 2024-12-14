package Contenido.Administradores;

import java.sql.Date;

public class Contrato {
    private int numRadicado;
    private int cotNum;
    private int empreNit;
    private Date fechaRec; 
    private double salarioBase;
    private String estado;
    private String tipoContrato;
    private int nitEmpresa; 
    private int rut;      

    public Contrato(int numRadicado, int cotNum, int empreNit, Date fechaRec, double salarioBase, String estado,
                    String tipoContrato, int nitEmpresa, int rut) {
        this.numRadicado = numRadicado;
        this.cotNum = cotNum;
        this.empreNit = empreNit;
        this.fechaRec = fechaRec;
        this.salarioBase = salarioBase;
        this.estado = estado;
        this.tipoContrato = tipoContrato;
        this.nitEmpresa = nitEmpresa;
        this.rut = rut;
    }

    public int getNumRadicado() {
        return numRadicado;
    }

    public void setNumRadicado(int numRadicado) {
        this.numRadicado = numRadicado;
    }

    public int getCotNum() {
        return cotNum;
    }

    public void setCotNum(int cotNum) {
        this.cotNum = cotNum;
    }

    public int getEmpreNit() {
        return empreNit;
    }

    public void setEmpreNit(int empreNit) {
        this.empreNit = empreNit;
    }

    public Date getFechaRec() {
        return fechaRec;
    }

    public void setFechaRec(Date fechaRec) {
        this.fechaRec = fechaRec;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public int getNitEmpresa() {
        return nitEmpresa;
    }

    public void setNitEmpresa(int nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }
}
