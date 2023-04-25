package domain.validaciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Validador {
    private final List<String> topPeoresContrasenias;
    private static final String nombreArchivo = "top_10000_peores_contraseñas.txt";

    public Validador(){
        topPeoresContrasenias = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                topPeoresContrasenias.add(linea);
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public boolean validar(CredencialDeAcceso credencialDeAcceso){
        return !this.esDebil(credencialDeAcceso.getContrasenia()) && !this.utilizaCredencialesPorDefecto(credencialDeAcceso.getNombreUsuario(), credencialDeAcceso.getContrasenia()) && this.respetaPoliticasNIST(credencialDeAcceso);
    }

    public boolean esDebil(String contrasenia){
        return topPeoresContrasenias.contains(contrasenia);
    }

    public boolean utilizaCredencialesPorDefecto(String usuario, String contrasenia){
        return usuario.equals(contrasenia);
    }

    public boolean respetaPoliticasNIST(CredencialDeAcceso credencialDeAcceso){
        return this.respetaLongitud(credencialDeAcceso.getContrasenia()) && this.respetaComplejidad(credencialDeAcceso.getContrasenia()) && this.respetaRotacion(credencialDeAcceso);
    }

    public boolean respetaLongitud(String contrasenia) {
        return contrasenia.length() >= 8 && contrasenia.length() <= 64;
    }

    public boolean respetaComplejidad(String contrasenia){
        return this.tieneMayuscula(contrasenia) && this.tieneCaracterEspecial(contrasenia) && this.tieneNumero(contrasenia);
    }

    public boolean respetaRotacion(CredencialDeAcceso credencialDeAcceso){
        return !credencialDeAcceso.esTiempoDeRotarContrasenia();
    }

    public interface Condicion {
        boolean cumpleCondicion(char caracter);
    }

    public boolean cumpleCondicion(Condicion condicion, String contrasenia) {
        for (int i = 0; i < contrasenia.length(); i++) {
            char caracter = contrasenia.charAt(i);
            if (condicion.cumpleCondicion(caracter)) {
                return true;
            }
        }
        return false;
    }

    public boolean tieneCaracterEspecial(String contrasenia) {
        return this.cumpleCondicion(caracter -> !Character.isLetterOrDigit(caracter), contrasenia);
    }

    public boolean tieneMayuscula(String contrasenia) {
        return this.cumpleCondicion(Character::isUpperCase, contrasenia);
    }

    public boolean tieneNumero(String contrasenia){
        return this.cumpleCondicion(Character::isDigit, contrasenia);
    }
}
