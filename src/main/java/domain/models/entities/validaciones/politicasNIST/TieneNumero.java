package domain.models.entities.validaciones.politicasNIST;

import domain.models.entities.validaciones.CredencialDeAcceso;
import domain.models.entities.validaciones.Validacion;

import java.util.List;

public class TieneNumero implements Validacion {
    @Override
    public boolean validar(CredencialDeAcceso credencialDeAcceso) {
        String contrasenia = credencialDeAcceso.getContrasenia();
        List<Character> chars = contrasenia.chars().mapToObj(e -> (char)e).toList();
        return chars.stream().anyMatch(Character::isDigit);
    }
}
