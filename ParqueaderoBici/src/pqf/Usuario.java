package pqf;

import java.util.ArrayList;

public class Usuario extends Persona {
    private String contrasena;
    private ArrayList<Bicicleta> bicicletas;

    public Usuario(String nombre, String documento, String correo, String contrasena) {
        super(nombre, documento, correo);
        this.contrasena = contrasena;
        bicicletas = new ArrayList<Bicicleta>();
    }

    public void agregarBicicleta(Bicicleta bicicleta) {
        bicicletas.add(bicicleta);
    }

    public ArrayList<Bicicleta> getBicicletas() {
        return bicicletas;
    }

    public boolean validarContrasena(String clave) {
        return contrasena.equals(clave);
    }
}
