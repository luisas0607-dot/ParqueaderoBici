package pqf;

public class Administrador extends Persona {
    public Administrador(String nombre, String documento, String correo) {
        super(nombre, documento, correo);
    }

    public void mostrarReporte(Parqueadero parqueadero) {
        System.out.println("\n=== REPORTE DEL ADMINISTRADOR ===");
        System.out.println("Total de espacios: " + parqueadero.getTotalEspacios());
        System.out.println("Espacios disponibles: " + parqueadero.contarDisponibles());
        System.out.println("Espacios ocupados: "
                + (parqueadero.getTotalEspacios() - parqueadero.contarDisponibles()));
        System.out.println("Movimientos registrados: " + parqueadero.getHistorial().size());
        parqueadero.mostrarHistorial();
    }
}
