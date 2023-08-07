package domain.entidadesDeServicio;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

@Getter
@Setter
public class Entidad {
    private String nombre;
    private String localizacion;
    private List<Establecimiento> listaEstablecimientos;

    public Entidad() {
        this.listaEstablecimientos = new ArrayList<>();
    }

    public void agregarEstablecimientos(Establecimiento ... establecimientos){
        Collections.addAll(this.listaEstablecimientos, establecimientos);
    }

}


