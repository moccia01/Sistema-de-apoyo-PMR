package domain.cargaDeDatos;

import domain.EntidadPrestadora;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CargaEntidadesPrestadoras {
    private final List<EntidadPrestadora> entidadesPrestadoras;

    public CargaEntidadesPrestadoras() {
        entidadesPrestadoras = new ArrayList<>();
    }

    public void cargarDatosEntidadesPrestadoras(String nombreArchivo){
        LectorArchivoCSV lector = new LectorArchivoCSV();
        List<String[]> datosLeidos = lector.leerArchivoCSV(nombreArchivo, ";", 1);
        for(int i = 0; i < datosLeidos.toArray().length; i++){
            String[] campos = datosLeidos.get(i);
            String nombre = campos[0];
            EntidadPrestadora entidad = new EntidadPrestadora(nombre);
            entidadesPrestadoras.add(entidad);
        }
    }
}
