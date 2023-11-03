package domain.models.repositorios.localizaciones;

import domain.models.entities.services.georef.entities.Provincia;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioProvincias implements WithSimplePersistenceUnit {
    /*
    public List<Provincia> obtenerProvincia(){
        return entityManager()
                .persist()
                .getResultList();

    }
    */
    public void agregar(Provincia provincia) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(provincia);
        tx.commit();
    }

    public void guardarProvinciaEnNuestraBase(List<Provincia> provincias) {
        EntityTransaction tx = entityManager().getTransaction();

        try {
            tx.begin();
            provincias.forEach(provincia -> {
                entityManager().persist(provincia);
            });
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}
