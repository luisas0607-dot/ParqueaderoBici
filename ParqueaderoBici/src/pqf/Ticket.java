package pqf;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private String codigoBicicleta;
    private String nombreUsuario;
    private int numeroEspacio;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;

    public Ticket(Bicicleta bicicleta, int numeroEspacio) {
        this.codigoBicicleta = bicicleta.getCodigo();
        this.nombreUsuario = bicicleta.getUsuario().getNombre();
        this.numeroEspacio = numeroEspacio;
        this.horaEntrada = LocalDateTime.now();
        this.horaSalida = null;
    }

    public String getCodigoBicicleta() { return codigoBicicleta; }
    public boolean estaActivo() { return horaSalida == null; }

    public void registrarSalida() {
        horaSalida = LocalDateTime.now();
    }

    public void mostrarTicket() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Bicicleta: " + codigoBicicleta
                + " | Usuario: " + nombreUsuario
                + " | Espacio: " + numeroEspacio
                + " | Entrada: " + horaEntrada.format(formato)
                + " | Salida: " + (horaSalida == null ? "Pendiente" : horaSalida.format(formato)));
    }
}
