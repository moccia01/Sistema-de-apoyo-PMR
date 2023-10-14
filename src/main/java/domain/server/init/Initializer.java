package domain.server.init;

import domain.models.entities.builders.UsuarioBuilder;
import domain.models.entities.comunidad.*;

import domain.models.entities.converters.GradoDeConfianzaConstructor;
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
    private Usuario lucasBoldrini;
    private Usuario federicoMoccia;
    private Usuario tomasDAntoio;
    private Usuario facundoSu;
    private Usuario nahuelGimenez;
    private Comunidad CS2;
    private Comunidad BOCA;
    private Comunidad Formula1;

    public void agregar(GradoDeConfianza gradoDeConfianza){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(gradoDeConfianza);
        tx.commit();
    }

    public void init() throws IOException {
        //Esto es para los niveeles de confianza //TODO esto ya está en métodos de GradoDeConfianzaConverter
        confianzaNoConfiable = GradoDeConfianzaConstructor.crearGradoDeConfianzaNoConfiable();
        confianzaConReservas = GradoDeConfianzaConstructor.crearGradoDeConfianzaConReservas();
        confianzaConfiableNivel1 = GradoDeConfianzaConstructor.crearGradoDeConfianzaConfiable1();
        confianzaConfiableNivel2 = GradoDeConfianzaConstructor.crearGradoDeConfianzaConfiable2();

        agregar(confianzaConfiableNivel2);
        agregar(confianzaConfiableNivel1);
        agregar(confianzaConReservas);
        agregar(confianzaNoConfiable);

        //Esto es para los usuarios
        //Esta linea de abajo es para que no rompa por contexto estatico
        UsuarioBuilder usuarioBuilder = new UsuarioBuilder();

        lucasBoldrini = usuarioBuilder
                .conNombre("Lucas")
                .conApellido("Boldrini")
                .conCredencial("lBoldrini", "pepito123")
                .conMail("lucasBoldrini@outlook.com")
                .conTelefono("1567673002")
                .conLocalizacion("Buenos Aires", "Villa Bosch", "3 de Febrero", "Manuel Quintana 780")
                .conMiembros()
                .conTiempoConfigurado("SINAPUROS")  //"SINAPUROS" o "CUANDOSUCEDE"
                .comMedioConfigurado("Email")   // "Email" o "WhatsApp"
                .conGradoDeConfianza()
                .construir();
        
    }

}
