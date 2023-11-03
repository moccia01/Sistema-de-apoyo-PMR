package domain.models.entities.validaciones.politicasNIST;

import domain.models.entities.validaciones.CredencialDeAcceso;
import domain.models.entities.validaciones.Validacion;

public class Longitud implements Validacion {

    public boolean validar(CredencialDeAcceso credencialDeAcceso){
        String contrasenia = credencialDeAcceso.getContrasenia();
        return contrasenia.length() >= 8 && contrasenia.length() <= 64;
    }
}
