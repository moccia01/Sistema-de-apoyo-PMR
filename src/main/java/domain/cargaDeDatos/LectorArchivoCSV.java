package domain.cargaDeDatos;

import domain.validaciones.LectorArchivo;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivoCSV {
    private String token;
    public LectorArchivoCSV(String token) {
        this.token = token;
    }

    public List<String[]> obtenerRegistros(String nombreArchivo){
        LectorArchivo lector = new LectorArchivo();
        List<String[]> datosCSV = new ArrayList<>();
        List<String> lineas = lector.obtenerLineas(nombreArchivo);
        for(String linea: lineas){
            datosCSV.add(linea.split(token));
        }
        return datosCSV;
    }
}
