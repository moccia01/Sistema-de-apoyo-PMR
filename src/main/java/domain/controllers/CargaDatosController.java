package domain.controllers;

import domain.models.entities.admins.AdminDePlataforma;
import domain.models.entities.admins.cargaDeDatos.CargaEntidadesPrestadoras;
import domain.models.entities.admins.cargaDeDatos.CargaOrganismosControl;
import domain.models.entities.entidadesDeServicio.EntidadPrestadora;
import domain.models.entities.entidadesDeServicio.OrganismoDeControl;
import domain.models.repositorios.RepositorioEntidadesPrestadoras;
import domain.models.repositorios.RepositorioOrganismoDeControl;
import domain.server.Server;
import domain.server.exceptions.FileNotLoadedException;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class CargaDatosController extends Controller implements WithSimplePersistenceUnit {
    private RepositorioEntidadesPrestadoras repositorioEntidadesPrestadoras;
    private RepositorioOrganismoDeControl repositorioOrganismoDeControl;

    public CargaDatosController(RepositorioEntidadesPrestadoras repositorioEntidadesPrestadoras,
                                RepositorioOrganismoDeControl repositorioOrganismoDeControl) {
        this.repositorioEntidadesPrestadoras = repositorioEntidadesPrestadoras;
        this.repositorioOrganismoDeControl = repositorioOrganismoDeControl;
    }

    public void show(Context context, String recurso) {
        Map<String, Object> model = new HashMap<>();
        EntityManager entityManager = entityManager();
        AdminDePlataforma admin = super.adminLogueado(context, entityManager);
        model.put("nombre", admin.getNombre());
        model.put("lista", this.obtenerListaSegun(recurso));
        context.render("admins/cargaDeDatos/" + recurso + ".hbs", model);
    }

    private Object obtenerListaSegun(String recurso) {
        EntityManager entityManager = entityManager();
        switch (recurso) {
            case "entidades_prestadoras" -> {
                return this.repositorioEntidadesPrestadoras.obtenerEntidadesPrestadoras(entityManager);
            }
            case "organismos_de_control" -> {
                return this.repositorioOrganismoDeControl.obtenerOrganismosDeControl(entityManager);
            }
            default -> {
                return null;
            }
        }
    }

    public void upload(Context context, String recurso){
        UploadedFile uploadedFile = context.uploadedFile("csvFileInput");
        String token = context.formParam("token");

        if (!Objects.requireNonNull(uploadedFile).filename().equals("")) {
            try {
                String directory = "src/main/resources/uploaded/";
                Files.createDirectories(Paths.get(directory));
                String nombreArchivo = uploadedFile.filename();
                File tempFile = new File(directory, nombreArchivo);
                Path rutaDestino = tempFile.toPath();
                Files.copy(uploadedFile.content(), rutaDestino, StandardCopyOption.REPLACE_EXISTING);
                this.cargarDatosSegunRecurso(rutaDestino.toString(), context, recurso, token);
            } catch (IOException e) {
                e.printStackTrace();
                context.status(500);
                context.result("Error al procesar el archivo: " + e.getMessage());
                throw new RuntimeException(e);
            }
        } else {
            context.sessionAttribute("error_return", recurso);
            throw new FileNotLoadedException();
        }
    }

    public void cargarDatosSegunRecurso(String path, Context context, String recurso, String token) {
        EntityManager entityManager = entityManager();
        switch (recurso) {
            case "entidades_prestadoras" -> {
                CargaEntidadesPrestadoras loader = new CargaEntidadesPrestadoras(token);
                List<EntidadPrestadora> entidadesPrestadoras = loader.cargarDatos(path);
                EntityTransaction tx = entityManager.getTransaction();
                tx.begin();
                entidadesPrestadoras.forEach(entidadPrestadora -> this.repositorioEntidadesPrestadoras.agregar(entidadPrestadora, entityManager));
                tx.commit();
                context.redirect("/admin/entidades_prestadoras");
            }
            case "organismos_de_control" -> {
                CargaOrganismosControl loader = new CargaOrganismosControl(token);
                List<OrganismoDeControl> organismosDeControl = loader.cargarDatos(path);
                EntityTransaction tx = entityManager.getTransaction();
                tx.begin();
                organismosDeControl.forEach(organismoDeControl -> this.repositorioOrganismoDeControl.agregar(organismoDeControl, entityManager));
                tx.commit();
                context.redirect("/admin/organismos_de_control");
            }
        }
    }

}
