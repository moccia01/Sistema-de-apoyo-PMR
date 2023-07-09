package domain.comunidad;

import com.twilio.rest.api.v2010.account.incomingphonenumber.Local;
import domain.entidadesDeServicio.Entidad;
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
    private Entidad entidad;

    public Incidente(String descripcion, Entidad entidad) {
        this.descripcion = descripcion;
        this.entidad = entidad;
    }
}
