package domain.entidadesDeServicio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
public class Servicio{
    private long id;
    private String nombre;
    private Boolean estado;
}
