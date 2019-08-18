package infraestructure.repository;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class GastoRecord {

    private int id;
    private String titulo;
    private String tipo;
    private String descripcion;
    private BigDecimal valor;
    private Timestamp fecha;

    public GastoRecord(int id, String titulo, String tipo, String descripcion, BigDecimal valor, Timestamp fecha) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
