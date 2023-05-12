package domain.validaciones.politicasNIST;

import domain.validaciones.CredencialDeAcceso;
import domain.validaciones.Validacion;

import java.util.List;

public class TieneMayuscula implements Validacion {
    @Override
    public boolean validar(CredencialDeAcceso credencialDeAcceso) {
        String contrasenia = credencialDeAcceso.getContrasenia();
        List<Character> chars = contrasenia.chars().mapToObj(e -> (char)e).toList();
        return chars.stream().anyMatch(Character::isUpperCase);
    }
}
