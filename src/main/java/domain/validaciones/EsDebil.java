package domain.validaciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class EsDebil implements Validacion{
    private final List<String> topPeoresContrasenias;
    private static final String nombreArchivo = "top_10000_peores_contraseñas.txt";

    public EsDebil() {
        topPeoresContrasenias = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                topPeoresContrasenias.add(linea);
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    @Override
    public boolean validar(CredencialDeAcceso credencialDeAcceso){
        return !topPeoresContrasenias.contains(credencialDeAcceso.getContrasenia());
    }
}
