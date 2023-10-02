package domain.entidadesDeServicio;

import domain.db.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Embeddable
public class PrestacionDeServicio{

    @ManyToOne
    @JoinColumn(name = "entidad_id", referencedColumnName = "id")
    private Entidad entidad;

    @ManyToOne
    @JoinColumn(name = "establecimiento_id", referencedColumnName = "id")
    private Establecimiento establecimiento;

    @ManyToOne
    @JoinColumn(name = "servicio_id", referencedColumnName = "id")
    private Servicio servicio;

    public boolean esLaMismaQue(PrestacionDeServicio prestacion){
        return this.entidad == prestacion.getEntidad() && this.establecimiento == prestacion.getEstablecimiento() && this.servicio == prestacion.getServicio();
    }

}
