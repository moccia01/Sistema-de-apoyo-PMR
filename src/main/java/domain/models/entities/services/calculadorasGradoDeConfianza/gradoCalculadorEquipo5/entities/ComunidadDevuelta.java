package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComunidadDevuelta {
    public ComunidadApi5 comunidad;
    public double nuevoPuntaje;
    public Integer gradoDeConfianzaActual;
}
