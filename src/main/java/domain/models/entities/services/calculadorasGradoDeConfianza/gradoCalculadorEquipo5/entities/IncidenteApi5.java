package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class IncidenteApi5 {
    public long id;
    public LocalDateTime fechaApertura;
    public UsuarioApi5 usuarioReportador;
    public LocalDateTime fechaCierre;
    public UsuarioApi5 usuarioAnalizador;
    public ServicioApi5 servicioAfectado;
}
