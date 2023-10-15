package domain.controllers;

import domain.models.repositorios.*;

public class FactoryController {

    public static Object controller(String nombre){

        return switch (nombre) {
            case "incidentes" -> new IncidenteController(new RepositorioIncidentes(), new RepositorioServicios(), new RepositorioEstablecimientos(), new RepositorioEntidades(), new RepositorioComunidades());
            case "comunidades" -> new ComunidadController(new RepositorioComunidades(), new RepositorioUsuarios(), new RepositorioMiembros());
            case "login" -> new LoginController(new RepositorioUsuarios(), new RepositorioCredenciales(), new RepositorioTiemposConfiguracion(), new RepositorioGradosDeConfianza());
            case "entidad_prestadora" -> new EntidadPrestadoraController();
            case "organismo_de_control" -> new OrganismoDeControlController();
            case "ranking" -> new RankingController();
            default -> null;
        };
    }
}
