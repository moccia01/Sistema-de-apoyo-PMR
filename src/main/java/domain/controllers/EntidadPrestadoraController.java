package domain.controllers;

import domain.models.entities.cargaDeDatos.LectorArchivoCSV;
import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.entities.entidadesDeServicio.EntidadPrestadora;
import domain.models.repositorios.RepositorioEntidadesPrestadoras;
import domain.server.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EntidadPrestadoraController  extends Controller{
    private LectorArchivoCSV lectorDeArchivo;
    private RepositorioEntidadesPrestadoras repositorioEntidadesPrestadoras;

    public void show(Context context) {
        context.render("cargaDeDatos/cargaEntidades.hbs");
    }

    public void guardar(Context context){
        List<String[]> listaLeida;
        List<EntidadPrestadora> listaEntidadesPrestadoras = new ArrayList<>();
        //TODO VER DONDE SE GUARDA EL ARCHIVO PARA PODER OBTENERLO, VER COMO OBTENERLO
        //TODO LUEGO DE VER COMO OBTENERLO, AGARRAR Y UTILIZAR EL LECTOR PARA LEERLO
        //TODO LUEGO DE LEERLO, TRANSFORMAR List<String[]> A List<EntidadPrestadora>
        //TODO FINALMENTE, UTILIZAR EL FOREACH CON agregarEnRepo PARA GUARDAR CADA
        //TODO ENTIDADPRESTADORA QUE NO ESTE EN LA BASE
        //TODO APLICAR MISMA LÓGICA A ORGANISMO DE CONTROL, SI REPITEN CODIGO, VER
        //TODO COMO ABSTRAERLO. FIN
        String nombreArchivoCSV = null;
        //listaLeida = lectorDeArchivo.obtenerRegistros(context.uploadedFile(nombreArchivoCSV));    //Aca obtendría el contenido del archivo

        //Acá haría la transformación a List<EntidadPrestadora>


        //Itero para agregar a la base
        listaEntidadesPrestadoras.forEach(entidadPrestadora -> this.agregarEnRepo(entidadPrestadora));
    }

    public void agregarEnRepo(EntidadPrestadora entidadPrestadora){

        if(repositorioEntidadesPrestadoras.obtenerEntidadPrestadora(entidadPrestadora.getId()) == null){
            repositorioEntidadesPrestadoras.agregar(entidadPrestadora);
        }
    }
}
