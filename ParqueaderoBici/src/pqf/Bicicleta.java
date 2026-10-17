package pqf;

public class Bicicleta {
    private String codigo;
    private String color;
    private String tipo;
    private Usuario usuario;

    public Bicicleta(String codigo, String color, String tipo, Usuario usuario) {
        this.codigo = codigo;
        this.color = color;
        this.tipo = tipo;
        this.usuario = usuario;
    }

    public String getCodigo() { return codigo; }
    public String getColor() { return color; }
    public String getTipo() { return tipo; }
    public Usuario getUsuario() { return usuario; }

    public void mostrarDatos() {
        System.out.println("Codigo: " + codigo + " | Color: " + color
                + " | Tipo: " + tipo + " | Propietario: " + usuario.getNombre());
    }
}
