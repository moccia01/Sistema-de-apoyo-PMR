package domain.server.init;

import domain.models.entities.builders.*;
import domain.models.entities.comunidad.*;

import domain.models.entities.converters.GradoDeConfianzaConstructor;
import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.entities.entidadesDeServicio.Establecimiento;
import domain.models.entities.entidadesDeServicio.Servicio;
import domain.models.entities.mensajes.Configuraciones.CuandoSucede;
import domain.models.entities.mensajes.Configuraciones.SinApuros;
import domain.models.entities.mensajes.Configuraciones.TiempoConfigurado;
import domain.models.repositorios.*;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.EntityTransaction;
import java.io.IOException;

@Setter
@Getter
public class Initializer implements WithSimplePersistenceUnit {
    private GradoDeConfianza confianzaConfiableNivel2;
    private GradoDeConfianza confianzaConfiableNivel1;
    private GradoDeConfianza confianzaConReservas;
    private GradoDeConfianza confianzaNoConfiable;



    public void init(){
        EntityTransaction tx = entityManager().getTransaction();
        RepositorioGradosDeConfianza repositorioGradosDeConfianza = new RepositorioGradosDeConfianza();

        GradoDeConfianzaConstructor gradoDeConfianzaConstructor = new GradoDeConfianzaConstructor();

        //Esto es para los niveeles de confianza
        GradoDeConfianza confianzaNoConfiable = gradoDeConfianzaConstructor.crearGradoDeConfianzaNoConfiable();
        GradoDeConfianza confianzaConReservas = gradoDeConfianzaConstructor.crearGradoDeConfianzaConReservas();
        GradoDeConfianza confianzaConfiableNivel1 = gradoDeConfianzaConstructor.crearGradoDeConfianzaConfiable1();
        GradoDeConfianza confianzaConfiableNivel2 = gradoDeConfianzaConstructor.crearGradoDeConfianzaConfiable2();


        this.confianzaConfiableNivel2 = new GradoDeConfianza();
        this.confianzaConfiableNivel1 = new GradoDeConfianza();
        this.confianzaConReservas = new GradoDeConfianza();
        this.confianzaNoConfiable = new GradoDeConfianza();

        this.confianzaConfiableNivel2.setNombreGradoConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_2);
        this.confianzaConfiableNivel2.setPuntosMinimos(5.0);
        this.confianzaConfiableNivel2.setGradoAnterior(this.confianzaConfiableNivel1);

        this.confianzaConfiableNivel1.setNombreGradoConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_1);
        this.confianzaConfiableNivel1.setPuntosMinimos(3.5);
        this.confianzaConfiableNivel1.setPuntosMaximos(5.0);
        this.confianzaConfiableNivel1.setGradoSiguiente(this.confianzaConfiableNivel2);
        this.confianzaConfiableNivel1.setGradoAnterior(this.confianzaConReservas);

        this.confianzaConReservas.setNombreGradoConfianza(NombreGradoConfianza.CON_RESERVAS);
        this.confianzaConReservas.setPuntosMinimos(2.0);
        this.confianzaConReservas.setPuntosMaximos(3.0);
        this.confianzaConReservas.setGradoAnterior(this.confianzaNoConfiable);
        this.confianzaConReservas.setGradoSiguiente(this.confianzaConfiableNivel1);

        this.confianzaNoConfiable.setNombreGradoConfianza(NombreGradoConfianza.NO_CONFIABLE);
        this.confianzaNoConfiable.setPuntosMaximos(2.0);
        this.confianzaNoConfiable.setGradoSiguiente(this.confianzaConReservas);

        repositorioGradosDeConfianza.agregar(this.confianzaConfiableNivel2);
        repositorioGradosDeConfianza.agregar(this.confianzaConfiableNivel1);
        repositorioGradosDeConfianza.agregar(this.confianzaConReservas);
        repositorioGradosDeConfianza.agregar(this.confianzaNoConfiable);


        //TODO faltan las cosas de georef
    }
}
