package domain.server.init;

import domain.models.entities.comunidad.*;
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
    private Comunidad Sequitos;
    private Comunidad TERRORISTAS;
    private Comunidad BOCA;
    private Miembro Lucas;
    private Miembro Fede;
    private Miembro Tomi;
    private Miembro Facu;
    private Miembro Sofia;
    private Miembro Lucrecia;
    private Miembro Agustin;
    private Miembro Florencia;
    private Miembro Cristiano;
    private Usuario elLucas;
    private Usuario elFede;
    private Usuario elTomi;
    private Usuario elFacu;
    private Usuario laSofia;
    private Usuario laLucrecia;
    private Usuario elAgustin;
    private Usuario laFlorencia;
    private Usuario elCristiano;


    public void init0() {
        Comunidad TERRORISTAS = new Comunidad();
        Comunidad BOCA = new Comunidad();
        Comunidad Sequitos = new Comunidad();

        elLucas = new Usuario();
        elLucas.setMail("federico21433@hotmail.com");
        elLucas.setNombre("lucas");
        elLucas.setApellido("Boldrini");
        elLucas.setTelefono("1525574474");

        elFede = new Usuario();
        elFede.setMail("federico21433@hotmail.com");
        elFede.setNombre("Fede");
        elFede.setApellido("Moccia");
        elFede.setTelefono("1525574474");

        elTomi = new Usuario();
        elTomi.setMail("federico21433@hotmail.com");
        elTomi.setNombre("Tomas");
        elTomi.setApellido("D´Antonio");
        elTomi.setTelefono("1525574474");

        elFacu = new Usuario();
        elFacu.setMail("federico21433@hotmail.com");
        elFacu.setNombre("Facu");
        elFacu.setApellido("Su");
        elFacu.setTelefono("1525574474");

        laSofia = new Usuario();
        laSofia.setMail("federico21433@hotmail.com");
        laSofia.setNombre("Sofia");
        laSofia.setApellido("qseyo");
        laSofia.setTelefono("1525574474");

        laLucrecia = new Usuario();
        laLucrecia.setMail("federico21433@hotmail.com");
        laLucrecia.setNombre("lucas");
        elLucas.setApellido("dividir");
        laLucrecia.setTelefono("1525574474");

        elAgustin  = new Usuario();
        elAgustin.setMail("federico21433@hotmail.com");
        elAgustin.setNombre("lucas");
        elAgustin.setApellido("resta");
        elAgustin.setTelefono("1525574474");

        elCristiano = new Usuario();
        elCristiano.setMail("federico21433@hotmail.com");
        elCristiano.setNombre("lucas");
        elCristiano.setApellido("suma");
        elCristiano.setTelefono("1525574474");

        laFlorencia = new Usuario();
        laFlorencia.setMail("federico21433@hotmail.com");
        laFlorencia.setNombre("Flor");
        laFlorencia.setApellido("pepito");
        laFlorencia.setTelefono("1525574474");

        Lucas = new Miembro(elLucas, Rol.MIEMBRO, TERRORISTAS);
        Fede = new Miembro(elFede, Rol.MIEMBRO, TERRORISTAS);
        Tomi = new Miembro(elTomi, Rol.ADMINISTRADOR, TERRORISTAS);
        Facu = new Miembro(elFacu, Rol.MIEMBRO, TERRORISTAS);

        Sofia = new Miembro(laSofia, Rol.ADMINISTRADOR, BOCA);
        Lucrecia = new Miembro(laLucrecia, Rol.ADMINISTRADOR, BOCA);
        Agustin = new Miembro(elAgustin, Rol.MIEMBRO, BOCA);

        Cristiano = new Miembro(elCristiano, Rol.ADMINISTRADOR, Sequitos);
        Florencia = new Miembro(laFlorencia, Rol.MIEMBRO, Sequitos);


    }

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
