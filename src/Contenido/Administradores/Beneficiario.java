package Contenido.Administradores;
import java.sql.Date;

public class Beneficiario {
    private int num_doc; 
    private String tipo_doc; 
    private int c_doc; 
    private String parentesco;
    
    public Beneficiario(int num_doc, String tipo_doc, int c_doc, String parentesco){
        this.num_doc = num_doc;
        this.tipo_doc = tipo_doc;
        this.c_doc = c_doc;
        this.parentesco = parentesco;
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

    public int getC_doc() {
        return c_doc;
    }

    public void setC_doc(int c_doc) {
        this.c_doc = c_doc;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    } 
}
