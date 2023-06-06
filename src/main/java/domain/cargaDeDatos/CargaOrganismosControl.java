package domain.cargaDeDatos;

import domain.OrganismoDeControl;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CargaOrganismosControl {
    private final List<OrganismoDeControl> organismosDeControl;

    public CargaOrganismosControl() {
        organismosDeControl = new ArrayList<>();
    }

    public void cargarDatosOrganismosDeControl(String nombreArchivo){
        LectorArchivoCSV lector = new LectorArchivoCSV();
        List<String[]> datosLeidos = lector.leerArchivoCSV(nombreArchivo, ";", 1);
        for(int i = 0; i < datosLeidos.toArray().length; i++){
            String[] campos = datosLeidos.get(i);
            String nombre = campos[0];
            OrganismoDeControl entidad = new OrganismoDeControl(nombre);
            organismosDeControl.add(entidad);
        }
    }
}
