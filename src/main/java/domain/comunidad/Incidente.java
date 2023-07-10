package domain.comunidad;

import com.twilio.rest.api.v2010.account.incomingphonenumber.Local;
import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.Establecimiento;
import domain.entidadesDeServicio.PrestacionDeServicio;
import domain.entidadesDeServicio.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Incidente {
    public LocalDate fechaApertura;
    public LocalDate fechaCierre;
    private String descripcion;
    private Boolean estado;
    private PrestacionDeServicio prestacionDeServicio;
    private Localizacion localizacion;

    public Incidente(String descripcion, PrestacionDeServicio prestacion) {
        this.descripcion = descripcion;
        this.prestacionDeServicio = prestacion;
    }
}
