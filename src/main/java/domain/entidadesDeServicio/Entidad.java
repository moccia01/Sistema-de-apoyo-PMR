package domain.entidadesDeServicio;

import lombok.Getter;
import lombok.Setter;

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

    public void agregarEstablecimientos(Establecimiento ... establecimientos){
        Collections.addAll(this.listaEstablecimientos, establecimientos);
    }
    public String leerInforme(String Informe) {
        try {
            return Files.readString(Paths.get(Informe));
        } catch (IOException e) {
            System.out.println("Error al leer el Informe: " + e.getMessage());
            return null;
        }
    }

}


