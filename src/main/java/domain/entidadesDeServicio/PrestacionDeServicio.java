package domain.entidadesDeServicio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestacionDeServicio {
    private Entidad entidad;
    private Establecimiento establecimiento;
    private Servicio servicio;
}
