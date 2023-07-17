package domain.rankings;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Informe {
    public List<GeneradorDeRankings> rankings;
    public String descripcion ;

    public void generarInforme(List<List<GeneradorDeRankings>> listas, String descripcion, String nombreArchivo) {
        try {
            FileWriter writer = new FileWriter(nombreArchivo);
            // Escribir la descripción en el informe
            writer.write("Descripción: " + descripcion + "\n\n");
            // Escribir los elementos de las listas en el informe
            for (int i = 0; i < listas.size(); i++) {
                writer.write("Lista " + (i + 1) + ":\n");
                List<GeneradorDeRankings> lista = listas.get(i);
                for (GeneradorDeRankings elemento : lista) {
                    writer.write(elemento + "\n");
                }
                writer.write("\n");
            }
            writer.close();

        } catch (IOException e) {

        }
    }

    }

