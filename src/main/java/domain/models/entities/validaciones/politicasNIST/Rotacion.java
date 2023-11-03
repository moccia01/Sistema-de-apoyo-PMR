package domain.models.entities.validaciones.politicasNIST;

import domain.models.entities.validaciones.CredencialDeAcceso;
import domain.models.entities.validaciones.Validacion;

public class Rotacion implements Validacion {

    public boolean validar(CredencialDeAcceso credencialDeAcceso){
        return !credencialDeAcceso.esTiempoDeRotarContrasenia();
    }
}
