package pqf;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Parqueadero parqueadero = new Parqueadero(5);
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        Mail mail = new Mail();

        Administrador administrador = new Administrador(
                "Administrador principal", "0000", "admin@parqueadero.local");

        int opcion = 0;

        do {
            System.out.println("\n======================================");
            System.out.println("     SISTEMA PARQUEADERO DE BICIS");
            System.out.println("======================================");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Registrar bicicleta");
            System.out.println("3. Registrar ingreso");
            System.out.println("4. Registrar salida");
            System.out.println("5. Ver espacios");
            System.out.println("6. Ver historial de tickets");
            System.out.println("7. Reporte del administrador");
            System.out.println("8. Salir");
            System.out.print("Selecciona una opcion: ");

            if (!teclado.hasNextInt()) {
                System.out.println("Debes escribir un numero.");
                teclado.nextLine();
                continue;
            }

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRO DE USUARIO ---");
                    System.out.print("Nombre: ");
                    String nombre = teclado.nextLine();
                    System.out.print("Documento: ");
                    String documento = teclado.nextLine();
                    System.out.print("Correo: ");
                    String correo = teclado.nextLine();
                    System.out.print("Contrasena: ");
                    String contrasena = teclado.nextLine();

                    if (buscarUsuario(usuarios, documento) != null) {
                        System.out.println("Ya existe un usuario con ese documento.");
                    } else {
                        usuarios.add(new Usuario(nombre, documento, correo, contrasena));
                        System.out.println("Usuario registrado correctamente.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- REGISTRO DE BICICLETA ---");
                    System.out.print("Documento del propietario: ");
                    String docDueno = teclado.nextLine();
                    Usuario dueno = buscarUsuario(usuarios, docDueno);

                    if (dueno == null) {
                        System.out.println("Primero debes registrar al usuario.");
                        break;
                    }

                    System.out.print("Codigo de la bicicleta: ");
                    String codigo = teclado.nextLine();

                    boolean repetido = false;
                    for (Usuario u : usuarios) {
                        for (Bicicleta b : u.getBicicletas()) {
                            if (b.getCodigo().equalsIgnoreCase(codigo)) {
                                repetido = true;
                            }
                        }
                    }

                    if (repetido) {
                        System.out.println("Ese codigo ya esta registrado.");
                        break;
                    }

                    System.out.print("Color: ");
                    String color = teclado.nextLine();
                    System.out.print("Tipo (urbana, montana, etc.): ");
                    String tipo = teclado.nextLine();

                    Bicicleta bicicleta = new Bicicleta(codigo, color, tipo, dueno);
                    dueno.agregarBicicleta(bicicleta);
                    System.out.println("Bicicleta registrada correctamente.");
                    bicicleta.mostrarDatos();
                    break;

                case 3:
                    System.out.print("Codigo de la bicicleta que ingresa: ");
                    String codigoEntrada = teclado.nextLine();
                    Bicicleta biciEntrada = buscarBicicleta(usuarios, codigoEntrada);

                    if (biciEntrada == null) {
                        System.out.println("La bicicleta no esta registrada.");
                    } else if (parqueadero.codigoEstaParqueado(codigoEntrada)) {
                        System.out.println("Esa bicicleta ya esta dentro del parqueadero.");
                    } else {
                        parqueadero.registrarIngreso(biciEntrada, mail);
                    }
                    break;

                case 4:
                    System.out.print("Codigo de la bicicleta que sale: ");
                    String codigoSalida = teclado.nextLine();
                    parqueadero.registrarSalida(codigoSalida, mail);
                    break;

                case 5:
                    parqueadero.mostrarEspacios();
                    break;

                case 6:
                    parqueadero.mostrarHistorial();
                    break;

                case 7:
                    administrador.mostrarReporte(parqueadero);
                    break;

                case 8:
                    System.out.println("Gracias por usar el sistema.");
                    break;

                default:
                    System.out.println("Opcion no valida. Elige una opcion del 1 al 8.");
                    break;
            }

        } while (opcion != 8);

        teclado.close();
    }

    public static Usuario buscarUsuario(ArrayList<Usuario> usuarios, String documento) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getDocumento().equalsIgnoreCase(documento)) {
                return usuarios.get(i);
            }
        }
        return null;
    }

    public static Bicicleta buscarBicicleta(ArrayList<Usuario> usuarios, String codigo) {
        for (int i = 0; i < usuarios.size(); i++) {
            ArrayList<Bicicleta> bicicletas = usuarios.get(i).getBicicletas();
            for (int j = 0; j < bicicletas.size(); j++) {
                if (bicicletas.get(j).getCodigo().equalsIgnoreCase(codigo)) {
                    return bicicletas.get(j);
                }
            }
        }
        return null;
    }
}
