package domain.validaciones.politicasNIST;

import domain.validaciones.CredencialDeAcceso;
import domain.validaciones.Validacion;

public class Longitud implements Validacion {

    public boolean validar(CredencialDeAcceso credencialDeAcceso){
        String contrasenia = credencialDeAcceso.getContrasenia();
        return contrasenia.length() >= 8 && contrasenia.length() <= 64;
    }
}
