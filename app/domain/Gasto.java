package domain;

import org.joda.time.DateTime;

import java.math.BigDecimal;

public class Gasto {

    private int id;
    private String titulo;
    private Tipo tipo;
    private String descripcion;
    private BigDecimal valor;
    private DateTime fecha;

    public Gasto(int id, String titulo, Tipo tipo, String descripcion, BigDecimal valor, DateTime fecha) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public DateTime getFecha() {
        return fecha;
    }

    public void setFecha(DateTime fecha) {
        this.fecha = fecha;
    }
}
