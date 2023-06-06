package domain.cargaDeDatos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivoCSV {

    public LectorArchivoCSV() {
    }

    public List<String[]> leerArchivoCSV(String nombreArchivo, String token, int cantidadCampos) {
        List<String[]> datosCSV = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(token);

                if (campos.length == cantidadCampos) {
                    datosCSV.add(campos);
                } else {
                    System.out.println("Error: la línea no contiene la cantidad de campos esperados.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        return datosCSV;
    }
}
