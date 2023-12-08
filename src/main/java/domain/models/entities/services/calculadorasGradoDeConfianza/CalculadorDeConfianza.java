package domain.models.entities.services.calculadorasGradoDeConfianza;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.Incidente;
import domain.models.entities.comunidad.Usuario;

import java.io.IOException;
import java.util.List;

public interface CalculadorDeConfianza {

    void calcularGradoConfianzaPara(List<Usuario> usuario, List<Comunidad> comunidad, List<Incidente> incidentes) throws IOException;
}
