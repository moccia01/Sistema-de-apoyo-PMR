package domain.models.repositorios;

import domain.models.entities.entidadesDeServicio.OrganismoDeControl;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;

public class RepositorioOrganismoDeControl implements WithSimplePersistenceUnit {

    public void agregar(OrganismoDeControl organismoDeControl){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(organismoDeControl);
        tx.commit();
    }







}
