package pqf;

import java.util.ArrayList;

public class Parqueadero {
    private Espacio[] espacios;
    private ArrayList<Ticket> historial;

    public Parqueadero(int cantidadEspacios) {
        espacios = new Espacio[cantidadEspacios];
        for (int i = 0; i < espacios.length; i++) {
            espacios[i] = new Espacio(i + 1);
        }
        historial = new ArrayList<Ticket>();
    }

    public int getTotalEspacios() { return espacios.length; }
    public ArrayList<Ticket> getHistorial() { return historial; }

    public int contarDisponibles() {
        int disponibles = 0;
        for (int i = 0; i < espacios.length; i++) {
            if (!espacios[i].estaOcupado()) {
                disponibles++;
            }
        }
        return disponibles;
    }

    public boolean registrarIngreso(Bicicleta bicicleta, Mail mail) {
        for (int i = 0; i < espacios.length; i++) {
            if (!espacios[i].estaOcupado()) {
                espacios[i].ocupar(bicicleta);
                Ticket ticket = new Ticket(bicicleta, espacios[i].getNumero());
                historial.add(ticket);
                System.out.println("Ingreso registrado. Espacio asignado: " + espacios[i].getNumero());
                ticket.mostrarTicket();
                mail.enviarAviso(bicicleta.getUsuario().getCorreo(),
                        "La bicicleta " + bicicleta.getCodigo() + " ingreso al parqueadero.");
                return true;
            }
        }
        System.out.println("No hay espacios disponibles. No se puede ingresar la bicicleta.");
        return false;
    }

    public boolean registrarSalida(String codigo, Mail mail) {
        for (int i = 0; i < espacios.length; i++) {
            if (espacios[i].estaOcupado()
                    && espacios[i].getBicicleta().getCodigo().equalsIgnoreCase(codigo)) {
                Bicicleta bicicleta = espacios[i].getBicicleta();

                for (int j = historial.size() - 1; j >= 0; j--) {
                    Ticket ticket = historial.get(j);
                    if (ticket.getCodigoBicicleta().equalsIgnoreCase(codigo) && ticket.estaActivo()) {
                        ticket.registrarSalida();
                        System.out.println("Salida registrada:");
                        ticket.mostrarTicket();
                        break;
                    }
                }

                espacios[i].liberar();
                mail.enviarAviso(bicicleta.getUsuario().getCorreo(),
                        "La bicicleta " + codigo + " salio del parqueadero.");
                System.out.println("Espacio " + (i + 1) + " liberado.");
                return true;
            }
        }
        System.out.println("No se encontro una bicicleta con ese codigo dentro del parqueadero.");
        return false;
    }

    public void mostrarEspacios() {
        System.out.println("\n=== ESTADO DE LOS ESPACIOS ===");
        for (int i = 0; i < espacios.length; i++) {
            espacios[i].mostrarEstado();
        }
        System.out.println("Disponibles: " + contarDisponibles() + " de " + espacios.length);
    }

    public void mostrarHistorial() {
        System.out.println("\n=== HISTORIAL DE TICKETS ===");
        if (historial.isEmpty()) {
            System.out.println("Todavia no hay movimientos.");
        } else {
            for (int i = 0; i < historial.size(); i++) {
                System.out.print((i + 1) + ". ");
                historial.get(i).mostrarTicket();
            }
        }
    }

    public boolean codigoEstaParqueado(String codigo) {
        for (int i = 0; i < espacios.length; i++) {
            if (espacios[i].estaOcupado()
                    && espacios[i].getBicicleta().getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            }
        }
        return false;
    }
}
