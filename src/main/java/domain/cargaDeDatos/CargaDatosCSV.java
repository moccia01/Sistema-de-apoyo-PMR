package domain.cargaDeDatos;

import domain.EntidadPrestadora;
import domain.OrganismoDeControl;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CargaDatosCSV {
    private List<EntidadPrestadora> entidadesPrestadoras;
    private List<OrganismoDeControl> organismosDeControl;

    public CargaDatosCSV() {
        entidadesPrestadoras = new ArrayList<>();
        organismosDeControl = new ArrayList<>();
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

    public void cargarDatosEntidadesPrestadoras(String nombreArchivo){
        List<String[]> datosLeidos = this.leerArchivoCSV(nombreArchivo, ";", 1);
        for(int i = 0; i < datosLeidos.toArray().length; i++){
            String[] campos = datosLeidos.get(i);
            String nombre = campos[0];
            EntidadPrestadora entidad = new EntidadPrestadora(nombre);
            entidadesPrestadoras.add(entidad);

        }
    }

    public void cargarDatosOrganismosDeControl(String nombreArchivo){
        List<String[]> datosLeidos = this.leerArchivoCSV(nombreArchivo, ";", 1);
        for(int i = 0; i < datosLeidos.toArray().length; i++){
            String[] campos = datosLeidos.get(i);
            String nombre = campos[0];
            OrganismoDeControl entidad = new OrganismoDeControl(nombre);
            organismosDeControl.add(entidad);
        }
    }
}
