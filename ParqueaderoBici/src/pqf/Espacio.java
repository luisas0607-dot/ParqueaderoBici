package pqf;

public class Espacio {
    private int numero;
    private boolean ocupado;
    private Bicicleta bicicleta;

    public Espacio(int numero) {
        this.numero = numero;
        this.ocupado = false;
        this.bicicleta = null;
    }

    public int getNumero() { return numero; }
    public boolean estaOcupado() { return ocupado; }
    public Bicicleta getBicicleta() { return bicicleta; }

    public void ocupar(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
        this.ocupado = true;
    }

    public void liberar() {
        this.bicicleta = null;
        this.ocupado = false;
    }

    public void mostrarEstado() {
        if (ocupado) {
            System.out.println("Espacio " + numero + ": OCUPADO - " + bicicleta.getCodigo());
        } else {
            System.out.println("Espacio " + numero + ": DISPONIBLE");
        }
    }
}
