package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDevuelto {
    public UsuarioApi5 usuario;
    public double nuevoPuntaje;
    public Integer gradoDeConfianzaActual;
}
