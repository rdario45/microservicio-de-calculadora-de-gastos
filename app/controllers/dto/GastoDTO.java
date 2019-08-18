package controllers.dto;

public class GastoDTO {

    private int id;
    private String titulo;
    private String tipo;
    private String descripcion;
    private String valor;
    private String fecha;

    public GastoDTO() {
        // to json serialize
    }

    public GastoDTO(int id, String titulo, String tipo, String descripcion, String valor, String fecha) {
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
