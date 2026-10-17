package pqf;

public class Mail {
    // Esta clase simula un aviso por correo; no envia correos reales.
    public void enviarAviso(String destinatario, String mensaje) {
        System.out.println("\n--- AVISO DE CORREO (SIMULADO) ---");
        System.out.println("Para: " + destinatario);
        System.out.println("Mensaje: " + mensaje);
        System.out.println("---------------------------------");
    }
}
