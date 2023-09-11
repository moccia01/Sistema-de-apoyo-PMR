package domain.repositorios;

import domain.comunidades.Comunidad;
import domain.comunidades.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class RepoComunidades implements WithSimplePersistenceUnit {

    public List<Comunidad> obtenerTodasLasComunidades(){
        return null;
    }

    public List<Comunidad> obtenerComunidadesDe(Usuario usuario){
        /*
            EntityManager
            tx.begin()

            select

            tx.commit()
        */
        return entityManager().createQuery("from Comunidad ").getResultList();
    }
}
