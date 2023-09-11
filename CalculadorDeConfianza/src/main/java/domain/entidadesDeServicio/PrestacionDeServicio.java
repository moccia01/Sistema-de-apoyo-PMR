package domain.entidadesDeServicio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestacionDeServicio {

    private Entidad entidad;

    private Establecimiento establecimiento;

    private Servicio servicio;

    public boolean esLaMismaQue(PrestacionDeServicio prestacion){
        return this.entidad == prestacion.getEntidad() && this.establecimiento == prestacion.getEstablecimiento() && this.servicio == prestacion.getServicio();
    }
}
