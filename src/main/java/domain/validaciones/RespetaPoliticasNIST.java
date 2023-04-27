package domain.validaciones;

public class RespetaPoliticasNIST implements Validacion{

    @Override
    public boolean validar(CredencialDeAcceso credencialDeAcceso){
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

    public boolean cumpleCondicion(Condicion condicion,String contrasenia) {
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
