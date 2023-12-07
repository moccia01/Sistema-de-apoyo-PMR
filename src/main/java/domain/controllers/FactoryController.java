package domain.controllers;

import domain.models.repositorios.*;

public class FactoryController {

    public static Object controller(String nombre){

        return switch (nombre) {
            case "incidentes" -> new IncidenteController(new RepositorioIncidentes(), new RepositorioComunidades(),  new RepositorioPrestaciones(), new RepositorioServicios(), new RepositorioEstablecimientos(), new RepositorioEntidades());
            case "comunidades" -> new ComunidadController(new RepositorioComunidades(), new RepositorioMiembros());
            case "login" -> new LoginController(new RepositorioUsuarios(), new RepositorioCredenciales(), new RepositorioTiemposConfiguracion(), new RepositorioGradosDeConfianza());
            case "miembros" -> new MiembroController(new RepositorioMiembros(), new RepositorioComunidades(), new RepositorioUsuarios(),new RepositorioCredenciales());
            case "carga_datos" -> new CargaDatosController(new RepositorioEntidadesPrestadoras(), new RepositorioOrganismoDeControl());
            case "ranking" -> new RankingController(new RepositorioIncidentes());
            case  "admin" -> new AdminController(new RepositorioAdmins());
            case "usuario" -> new UsuarioController(new RepositorioTiemposConfiguracion());
            default -> null;
        };
    }
}
