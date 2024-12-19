package Contenido.Bancos;

import java.sql.Date;

public class Pago {
    private int codigoPago;
    private int numRadicado;
    private Date fechaPago;
    private double valor;

    // Constructor adecuado, eliminando los parámetros innecesarios
    public Pago(int codigoPago, int numRadicado, Date fechaPago, double valor) {
        this.codigoPago = codigoPago;
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

    @Override
    public String toString() {
        return String.format(
            "Código de Pago: %d | Número Radicado: %d | Fecha de Pago: %s | Valor: %.2f",
            codigoPago, numRadicado, fechaPago, valor
        );
    }
}
