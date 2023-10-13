package domain.server.init;

import domain.models.entities.comunidad.GradoDeConfianza;
import domain.models.entities.comunidad.NombreGradoConfianza;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.EntityTransaction;

@Setter
@Getter
public class Initializer implements WithSimplePersistenceUnit {

    private GradoDeConfianza confianzaConfiableNivel2;
    private GradoDeConfianza confianzaConfiableNivel1;
    private GradoDeConfianza confianzaConReservas;
    private GradoDeConfianza confianzaNoConfiable;

    public void agregar(GradoDeConfianza gradoDeConfianza){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(gradoDeConfianza);
        tx.commit();
    }


    public void init() {
        confianzaConfiableNivel2 = new GradoDeConfianza();
        confianzaConfiableNivel1 = new GradoDeConfianza();
        confianzaConReservas = new GradoDeConfianza();
        confianzaNoConfiable = new GradoDeConfianza();

        confianzaConfiableNivel2.setNombreGradoConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_2);
        confianzaConfiableNivel2.setPuntosMinimos(5.0);
        confianzaConfiableNivel2.setGradoAnterior(confianzaConfiableNivel1);

        confianzaConfiableNivel1.setNombreGradoConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_1);
        confianzaConfiableNivel1.setPuntosMinimos(3.5);
        confianzaConfiableNivel1.setPuntosMaximos(5.0);
        confianzaConfiableNivel1.setGradoSiguiente(confianzaConfiableNivel2);
        confianzaConfiableNivel1.setGradoAnterior(confianzaConReservas);

        confianzaConReservas.setNombreGradoConfianza(NombreGradoConfianza.CON_RESERVAS);
        confianzaConReservas.setPuntosMinimos(2.0);
        confianzaConReservas.setPuntosMaximos(3.0);
        confianzaConReservas.setGradoAnterior(confianzaNoConfiable);
        confianzaConReservas.setGradoSiguiente(confianzaConfiableNivel1);

        confianzaNoConfiable.setNombreGradoConfianza(NombreGradoConfianza.NO_CONFIABLE);
        confianzaNoConfiable.setPuntosMaximos(2.0);
        confianzaNoConfiable.setGradoSiguiente(confianzaConReservas);

        agregar(confianzaConfiableNivel2);
        agregar(confianzaConfiableNivel1);
        agregar(confianzaConReservas);
        agregar(confianzaNoConfiable);
    }
}
