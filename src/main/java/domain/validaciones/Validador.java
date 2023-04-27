package domain.validaciones;

import java.util.ArrayList;
import java.util.List;

public class Validador {
    private final List<Validacion> validaciones;

    public Validador() {
        validaciones = new ArrayList<>();
        validaciones.add(new EsDebil());
        validaciones.add(new UsaCredencialesPorDefecto());
        validaciones.add(new RespetaPoliticasNIST());
    }

    public boolean validar(CredencialDeAcceso credencialDeAcceso) {
        return validaciones.stream().allMatch(v -> v.validar(credencialDeAcceso));
    }
}
