package domain.comunidad;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Incidente {
    private LocalDate fechaApertura;
    private LocalDate fechaCierre;
    private String descripcion;
    private Boolean estado;
}
