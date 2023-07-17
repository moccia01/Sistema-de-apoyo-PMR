package domain.entidadesDeServicio;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Entidad {
    private String nombre;
    private String localizacion;
    private List<Establecimiento> listaEstablecimientos;

    public void agregarEstablecimientos(Establecimiento ... establecimientos){
        Collections.addAll(this.listaEstablecimientos, establecimientos);
    }
}


