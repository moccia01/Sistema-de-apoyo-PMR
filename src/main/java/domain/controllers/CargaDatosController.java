package domain.controllers;

import domain.models.entities.cargaDeDatos.CargaEntidadesPrestadoras;
import domain.models.entities.cargaDeDatos.CargaOrganismosControl;
import domain.models.entities.entidadesDeServicio.EntidadPrestadora;
import domain.models.entities.entidadesDeServicio.OrganismoDeControl;
import domain.models.repositorios.RepositorioEntidadesPrestadoras;
import domain.models.repositorios.RepositorioOrganismoDeControl;
import domain.server.exceptions.FileNotLoadedException;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;



public class CargaDatosController extends Controller{
    private RepositorioEntidadesPrestadoras repositorioEntidadesPrestadoras;
    private RepositorioOrganismoDeControl repositorioOrganismoDeControl;

    public CargaDatosController(RepositorioEntidadesPrestadoras repositorioEntidadesPrestadoras,
                                RepositorioOrganismoDeControl repositorioOrganismoDeControl) {
        this.repositorioEntidadesPrestadoras = repositorioEntidadesPrestadoras;
        this.repositorioOrganismoDeControl = repositorioOrganismoDeControl;
    }

    public void show(Context context, String vista) {
        context.render("cargaDeDatos/" + vista);
    }

    public void upload(Context context, String recurso){
        UploadedFile uploadedFile = context.uploadedFile("csvFileInput");
        String token = context.formParam("token");
        if (uploadedFile != null) {
            try {
                String destino = "src/main/resources/uploaded/";
                Files.createDirectories(Paths.get(destino));
                String nombreArchivo = uploadedFile.filename();
                Path rutaDestino = Paths.get(destino, nombreArchivo);
                Files.copy(uploadedFile.content(), rutaDestino, StandardCopyOption.REPLACE_EXISTING);
                this.cargarDatosSegunRecurso(rutaDestino.toString(), context, recurso, token);

            } catch (IOException e) {
                e.printStackTrace();
                context.status(500);
                context.result("Error al procesar el archivo: " + e.getMessage());
                throw new RuntimeException(e);
            }
        } else {
            throw new FileNotLoadedException();
        }
    }

    public void cargarDatosSegunRecurso(String path, Context context, String recurso, String token) {
        switch (recurso) {
            case "entidades_prestadoras" -> {
                CargaEntidadesPrestadoras loader = new CargaEntidadesPrestadoras(token);
                List<EntidadPrestadora> entidadesPrestadoras = loader.cargarDatos(path);
                entidadesPrestadoras.forEach(entidadPrestadora -> this.repositorioEntidadesPrestadoras.agregar(entidadPrestadora));
                context.redirect("entidades_prestadoras");
            }
            case "organismos_de_control" -> {
                CargaOrganismosControl loader = new CargaOrganismosControl(token);
                List<OrganismoDeControl> organismosDeControl = loader.cargarDatos(path);
                organismosDeControl.forEach(organismoDeControl -> this.repositorioOrganismoDeControl.agregar(organismoDeControl));
                context.redirect("organismos_de_control");
            }
        }
    }
}
