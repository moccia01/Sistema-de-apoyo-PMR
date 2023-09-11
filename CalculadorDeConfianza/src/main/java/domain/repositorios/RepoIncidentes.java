package domain.repositorios;

import domain.comunidades.Incidente;
import domain.comunidades.Usuario;

import java.util.List;

public class RepoIncidentes {

    public List<Incidente> obtenerIncidentesDe(Usuario usuario){

        /*
            EntityManager
            tx.begin()

            select comunidad_id from miembro where usuario_id = usuario.id()

            tx.commit()
        */

        return null;
    }
}
