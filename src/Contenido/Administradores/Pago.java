package Contenido.Administradores;

import java.util.Date;

public class Pago {
    private int codigoPago;
    private int numeroDocumentoAfiliado;
    private String nombreCompleto;
    private int numRadicado;
    private Date fechaPago;
    private double valor;

    public Pago(int codigoPago, int numeroDocumentoAfiliado, String nombreCompleto, int numRadicado, Date fechaPago, double valor) {
        this.codigoPago = codigoPago;
        this.numeroDocumentoAfiliado = numeroDocumentoAfiliado;
        this.nombreCompleto = nombreCompleto;
        this.numRadicado = numRadicado;
        this.fechaPago = fechaPago;
        this.valor = valor;
    }

    public int getCodigoPago() {
        return codigoPago;
    }

    public void setCodigoPago(int codigoPago) {
        this.codigoPago = codigoPago;
    }

    public int getNumeroDocumentoAfiliado() {
        return numeroDocumentoAfiliado;
    }

    public void setNumeroDocumentoAfiliado(int numeroDocumentoAfiliado) {
        this.numeroDocumentoAfiliado = numeroDocumentoAfiliado;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getNumRadicado() {
        return numRadicado;
    }

    public void setNumRadicado(int numRadicado) {
        this.numRadicado = numRadicado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    public String toString() {
        return String.format(
            "Código de Pago: %d | Documento Afiliado: %d | Nombre: %s | Número Radicado: %d | Fecha de Pago: %s | Valor: %.2f",
            codigoPago, numeroDocumentoAfiliado, nombreCompleto, numRadicado, fechaPago, valor
        );
    }
}

