package domain.controllers;

import domain.models.entities.admins.AdminDePlataforma;
import domain.models.entities.comunidad.Usuario;
import domain.models.repositorios.RepositorioAdmins;
import domain.server.Server;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

public class AdminController extends Controller{
    private RepositorioAdmins repositorioAdmins;

    public AdminController(RepositorioAdmins repositorioAdmins) {
        this.repositorioAdmins = repositorioAdmins;
    }

    public void show(Context context) {
        context.sessionAttribute("admin_id", null);
        context.sessionAttribute("usuario_id", null);
        Map<String, Object> model = new HashMap<>();
        context.render("login/login.hbs", model);
    }

    public void login(Context context) {
        String username = context.formParam("username");
        String password = context.formParam("password");
        EntityManager entityManager = Server.entityManager();
        AdminDePlataforma adminDePlataforma = this.repositorioAdmins.obtenerAdmin(username, password, entityManager);
        if(adminDePlataforma != null) {
            context.sessionAttribute("admin_id", adminDePlataforma.getId());
            context.redirect("/admin/index");
        } else {
            Map<String, Object> model = new HashMap<>();
            context.render("errors/loginError.hbs", model);
        }
    }

    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        EntityManager entityManager = Server.entityManager();
        AdminDePlataforma admin = super.adminLogueado(context, entityManager);
        model.put("nombre", admin.getNombre());
        context.render("admins/index.hbs", model);
    }
}
