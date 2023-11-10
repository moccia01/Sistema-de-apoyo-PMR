package domain.controllers;

import domain.models.repositorios.*;

public class FactoryController {

    public static Object controller(String nombre){

        return switch (nombre) {
            case "incidentes" -> new IncidenteController(new RepositorioIncidentes(), new RepositorioServicios(), new RepositorioEstablecimientos(), new RepositorioEntidades(), new RepositorioComunidades(), new RepositorioUsuarios());
            case "comunidades" -> new ComunidadController(new RepositorioComunidades(), new RepositorioUsuarios(), new RepositorioMiembros());
            case "login" -> new LoginController(new RepositorioUsuarios(), new RepositorioCredenciales(), new RepositorioTiemposConfiguracion(), new RepositorioGradosDeConfianza());
            case "miembros" -> new MiembroController(new RepositorioMiembros(), new RepositorioTiemposConfiguracion(), new RepositorioComunidades(), new RepositorioUsuarios(),new RepositorioCredenciales());
            case "carga_datos" -> new CargaDatosController(new RepositorioEntidadesPrestadoras(), new RepositorioOrganismoDeControl());
            case "ranking" -> new RankingController(new RepositorioIncidentes(), new RepositorioServicios(), new RepositorioEstablecimientos(), new RepositorioEntidades(), new RepositorioComunidades());
            case  "admin" -> new AdminController(new RepositorioAdmins());
            case "usuario" -> new UsuarioController(new RepositorioUsuarios(), new RepositorioTiemposConfiguracion());
            default -> null;
        };
    }
}
