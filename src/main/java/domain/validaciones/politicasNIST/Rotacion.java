package domain.validaciones.politicasNIST;

import domain.validaciones.CredencialDeAcceso;
import domain.validaciones.Validacion;

public class Rotacion implements Validacion {

    public boolean validar(CredencialDeAcceso credencialDeAcceso){
        return !credencialDeAcceso.esTiempoDeRotarContrasenia();
    }
}
