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
        RepositorioGradosDeConfianza repositorioGradosDeConfianza = new RepositorioGradosDeConfianza();

        GradoDeConfianzaConstructor gradoDeConfianzaConstructor = new GradoDeConfianzaConstructor();
        GradoDeConfianza gradoconfiableNivel2 = repositorioGradosDeConfianza.obtenerGradoDeConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_2);

        if(gradoconfiableNivel2 == null) {
            this.confianzaNoConfiable = gradoDeConfianzaConstructor.crearGradoDeConfianzaNoConfiable();
            this.confianzaConReservas = gradoDeConfianzaConstructor.crearGradoDeConfianzaConReservas();
            this.confianzaConfiableNivel1 = gradoDeConfianzaConstructor.crearGradoDeConfianzaConfiable1();
            this.confianzaConfiableNivel2 = gradoDeConfianzaConstructor.crearGradoDeConfianzaConfiable2();

            repositorioGradosDeConfianza.agregar(this.confianzaConfiableNivel2);
            repositorioGradosDeConfianza.agregar(this.confianzaConfiableNivel1);
            repositorioGradosDeConfianza.agregar(this.confianzaConReservas);
            repositorioGradosDeConfianza.agregar(this.confianzaNoConfiable);
        } else {
            this.confianzaNoConfiable = repositorioGradosDeConfianza.obtenerGradoDeConfianza(NombreGradoConfianza.NO_CONFIABLE);
            this.confianzaConReservas = repositorioGradosDeConfianza.obtenerGradoDeConfianza(NombreGradoConfianza.CON_RESERVAS);
            this.confianzaConfiableNivel1 = repositorioGradosDeConfianza.obtenerGradoDeConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_1);
            this.confianzaConfiableNivel2 = repositorioGradosDeConfianza.obtenerGradoDeConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_2);
        }

        //TODO faltan las cosas de georef
    }
}
