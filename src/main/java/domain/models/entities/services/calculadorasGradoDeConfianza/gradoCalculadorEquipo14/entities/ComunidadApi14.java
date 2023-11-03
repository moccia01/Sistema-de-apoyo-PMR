package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ComunidadApi14 {
    public long id;
    public double puntosDeConfianza;
    public GradoDeConfianzaApi14 gradoDeConfianza;
    public List<UsuarioApi14> usuarios;
}
