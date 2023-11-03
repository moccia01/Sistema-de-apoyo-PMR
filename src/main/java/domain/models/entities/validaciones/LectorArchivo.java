package domain.models.entities.validaciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivo {

    public LectorArchivo() {
    }

    public List<String> obtenerLineas(String nombreArchivo) {
        List<String> lineas = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return lineas;
    }
}

