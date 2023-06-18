package domain.cargaDeDatos;
import java.util.ArrayList;
import java.util.List;

public abstract class CargaDatosTemplate <T>{
    private final String token;
    public CargaDatosTemplate(String token) {
        this.token = token;
    }

    public List<T> cargarDatos(String nombreArchivo){
        LectorArchivoCSV lectorCSV = new LectorArchivoCSV(token);
        List<T> datosCSV= new ArrayList<>();
        List<String[]> lineas = lectorCSV.obtenerRegistros(nombreArchivo);

        for(String[] campos: lineas) {
            T dato = this.transformarLinea(campos);
            datosCSV.add(dato);
        }
        return datosCSV;
    }

    protected abstract T transformarLinea(String[] campos);
}