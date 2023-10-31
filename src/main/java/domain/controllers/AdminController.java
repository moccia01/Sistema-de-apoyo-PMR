package domain.controllers;

import domain.models.entities.admins.AdminDePlataforma;
import domain.models.repositorios.RepositorioAdmins;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class AdminController extends Controller{
    private RepositorioAdmins repositorioAdmins;

    public AdminController(RepositorioAdmins repositorioAdmins) {
        this.repositorioAdmins = repositorioAdmins;
    }

    public void show(Context context) {
        context.sessionAttribute("admin_id", null);
        Map<String, Object> model = new HashMap<>();
        context.render("login/login.hbs", model);
    }

    public void login(Context context) {
        String username = context.formParam("username");
        String password = context.formParam("password");
        AdminDePlataforma adminDePlataforma = this.repositorioAdmins.obtenerAdmin(username, password);
        if(adminDePlataforma != null) {
            context.sessionAttribute("admin_id", adminDePlataforma.getId());
            context.redirect("/admin/index");
        } else {
            Map<String, Object> model = new HashMap<>();
            context.render("errors/loginError.hbs", model);
        }
    }

}
