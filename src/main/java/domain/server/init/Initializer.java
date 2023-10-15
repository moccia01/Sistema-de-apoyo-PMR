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



    public void init(){
        EntityTransaction tx = entityManager().getTransaction();
        RepositorioGradosDeConfianza repositorioGradosDeConfianza = new RepositorioGradosDeConfianza();
/*
        GradoDeConfianzaConstructor gradoDeConfianzaConstructor = new GradoDeConfianzaConstructor();

        //Esto es para los niveeles de confianza
        GradoDeConfianza confianzaNoConfiable = gradoDeConfianzaConstructor.crearGradoDeConfianzaNoConfiable();
        GradoDeConfianza confianzaConReservas = gradoDeConfianzaConstructor.crearGradoDeConfianzaConReservas();
        GradoDeConfianza confianzaConfiableNivel1 = gradoDeConfianzaConstructor.crearGradoDeConfianzaConfiable1();
        GradoDeConfianza confianzaConfiableNivel2 = gradoDeConfianzaConstructor.crearGradoDeConfianzaConfiable2();
*/

        GradoDeConfianza confianzaConfiableNivel2 = new GradoDeConfianza();
        GradoDeConfianza confianzaConfiableNivel1 = new GradoDeConfianza();
        GradoDeConfianza confianzaConReservas = new GradoDeConfianza();
        GradoDeConfianza confianzaNoConfiable = new GradoDeConfianza();

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

        repositorioGradosDeConfianza.agregar(confianzaConfiableNivel2);
        repositorioGradosDeConfianza.agregar(confianzaConfiableNivel1);
        repositorioGradosDeConfianza.agregar(confianzaConReservas);
        repositorioGradosDeConfianza.agregar(confianzaNoConfiable);



        //TODO: PASAR ESTO A UN TEST
        //Esto es para los usuarios
        //Esta linea de abajo es para que no rompa por contexto estatico
        TiempoConfigurado cuandoSucede = new CuandoSucede();

        UsuarioBuilder usuarioBuilder = new UsuarioBuilder();

        Usuario lucasBoldrini = usuarioBuilder.conNombre("Lucas").conApellido("Boldrini")
                .conCredencial("lBoldrini", "pepito123")
                .conMail("lboldrini@frba.utn.edu.ar").conTelefono("1567673002")
                .conTiempoConfigurado(new SinApuros())  //"SINAPUROS" o "CUANDOSUCEDE"
                .conMedioConfigurado("Email")   // "Email" o "WhatsApp"
                .conGradoDeConfianza(confianzaConfiableNivel1).construir();

        Usuario tomasDAntonio = usuarioBuilder.conNombre("Tomas").conApellido("DAntonio").conMail("tdantonio@frba.utn.edu.ar")
                .conCredencial("tdantonio", "aguanteboca12").conTelefono("1234567890")
                .conTiempoConfigurado(new SinApuros()).conMedioConfigurado("WhatsApp")
                .conGradoDeConfianza(confianzaConfiableNivel1).construir();


        Usuario federicoMoccia  = usuarioBuilder.conNombre("Federico").conApellido("Moccia")
                .conCredencial("fMoccia", "aguanteMetallicaNoMeImportaNada666")
                .conMail("fmoccia@frba.utn.edu.ar").conTelefono("3452654439")
                .conTiempoConfigurado(cuandoSucede)  //"SINAPUROS" o "CUANDOSUCEDE"
                .conMedioConfigurado("Email")   // "Email" o "WhatsApp"
                .conGradoDeConfianza(confianzaConfiableNivel1).construir();

        Usuario nahuGimenez  = usuarioBuilder.conNombre("Nahuel").conApellido("Gimenez")
                .conCredencial("nGimenez", "hola123")
                .conMail("ngimenez@frba.utn.edu.ar").conTelefono("1528658491")
                .conTiempoConfigurado(new SinApuros())  //"SINAPUROS" o "CUANDOSUCEDE"
                .conMedioConfigurado("Email")   // "Email" o "WhatsApp"
                .conGradoDeConfianza(confianzaConfiableNivel1).construir();

        Usuario facundoSu = usuarioBuilder.conNombre("Facundo").conApellido("Su")
                .conCredencial("facu123", "facu123")
                .conMail("fsu@frba.utn.edu.ar").conTelefono("1234567890")
                .conTiempoConfigurado(cuandoSucede).conMedioConfigurado("WhatsApp")
                .conGradoDeConfianza(confianzaConfiableNivel1).construir();

        ComunidadBuilder comunidadBuilder = new ComunidadBuilder();

        Comunidad bosterosDesdeLaCuna = comunidadBuilder.conNombre("Bosteros desde la cuna").conGradoDeConfianza(confianzaConfiableNivel1).construir();
        Comunidad citizenDesdeLaCuna = comunidadBuilder.conNombre("Citizen desde la cuna").conGradoDeConfianza(confianzaConfiableNivel1).construir();
        Comunidad operativosEnjoyers = comunidadBuilder.conNombre("Operativos enjoyers").conGradoDeConfianza(confianzaConfiableNivel1).construir();

        MiembroBuilder miembroBuilder = new MiembroBuilder();
        Miembro tomyBostero = miembroBuilder.conRol(Rol.ADMINISTRADOR).conUsuario(tomasDAntonio)
                .conComunidad(bosterosDesdeLaCuna).conRolTemporal(RolTemporal.OBSERVADOR).construir();
        Miembro nahuBostero = miembroBuilder.conRol(Rol.MIEMBRO).conUsuario(nahuGimenez)
                .conComunidad(bosterosDesdeLaCuna).conRolTemporal(RolTemporal.OBSERVADOR).construir();
        Miembro lucasBostero = miembroBuilder.conRol(Rol.MIEMBRO).conUsuario(lucasBoldrini)
                        .conComunidad(bosterosDesdeLaCuna).conRolTemporal(RolTemporal.OBSERVADOR).construir();
        bosterosDesdeLaCuna.agregarMiembros(tomyBostero, nahuBostero, lucasBostero);

        Miembro fedeCitizen = miembroBuilder.conRol(Rol.ADMINISTRADOR).conUsuario(federicoMoccia)
                .conComunidad(citizenDesdeLaCuna).conRolTemporal(RolTemporal.AFECTADO).construir();
        Miembro facuCitizen = miembroBuilder.conRol(Rol.MIEMBRO).conUsuario(facundoSu)
                        .conComunidad(citizenDesdeLaCuna).conRolTemporal(RolTemporal.OBSERVADOR).construir();
        citizenDesdeLaCuna.agregarMiembros(fedeCitizen, facuCitizen);

        Miembro tomyUTNSO = miembroBuilder.conRol(Rol.ADMINISTRADOR).conUsuario(tomasDAntonio)
                .conComunidad(operativosEnjoyers).conRolTemporal(RolTemporal.OBSERVADOR).construir();
        Miembro nahuUTNSO = miembroBuilder.conRol(Rol.MIEMBRO).conUsuario(nahuGimenez)
                .conComunidad(operativosEnjoyers).conRolTemporal(RolTemporal.OBSERVADOR).construir();
        Miembro lucasUTNSO = miembroBuilder.conRol(Rol.MIEMBRO).conUsuario(lucasBoldrini)
                .conComunidad(operativosEnjoyers).conRolTemporal(RolTemporal.OBSERVADOR).construir();
        Miembro fedeUTNSO = miembroBuilder.conRol(Rol.MIEMBRO).conUsuario(federicoMoccia)
                        .conComunidad(operativosEnjoyers).conRolTemporal(RolTemporal.AFECTADO).construir();
        operativosEnjoyers.agregarMiembros(tomyUTNSO, nahuUTNSO, lucasUTNSO, fedeUTNSO);

        Servicio escaleraMecanica = new Servicio();
        escaleraMecanica.setNombre("Escalera Mecanica");
        escaleraMecanica.setEstado(true);

        Servicio banio = new Servicio();
        banio.setNombre("Baño");
        banio.setEstado(true);

        Servicio ascensor = new Servicio();
        ascensor.setNombre("ascensor");
        ascensor.setEstado(true);

        EstablecimientoBuilder establecimientoBuilder = new EstablecimientoBuilder();
        Establecimiento campus = establecimientoBuilder.conNombre("Campus").conServicios(escaleraMecanica, banio, ascensor)
                .construir();
        Establecimiento medrano = establecimientoBuilder.conNombre("Medrano").conServicios(escaleraMecanica, banio, ascensor)
                .construir();

        Entidad utn = new Entidad();
        utn.setNombre("Universidad Tecnologica Nacional");
        utn.agregarEstablecimientos(campus, medrano);

        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
        repositorioUsuarios.agregar(tomasDAntonio);
        repositorioUsuarios.agregar(federicoMoccia);
        repositorioUsuarios.agregar(lucasBoldrini);
        repositorioUsuarios.agregar(nahuGimenez);
        repositorioUsuarios.agregar(facundoSu);

        RepositorioComunidades repositorioComunidades = new RepositorioComunidades();
        repositorioComunidades.agregar(bosterosDesdeLaCuna);
        repositorioComunidades.agregar(citizenDesdeLaCuna);
        repositorioComunidades.agregar(operativosEnjoyers);

        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
        repositorioMiembros.agregar(tomyBostero);
        repositorioMiembros.agregar(nahuBostero);
        repositorioMiembros.agregar(lucasBostero);
        repositorioMiembros.agregar(fedeCitizen);
        repositorioMiembros.agregar(facuCitizen);
        repositorioMiembros.agregar(tomyUTNSO);
        repositorioMiembros.agregar(fedeUTNSO);
        repositorioMiembros.agregar(lucasUTNSO);
        repositorioMiembros.agregar(nahuUTNSO);

        RepositorioServicios repositorioServicios = new RepositorioServicios();
        repositorioServicios.agregar(banio);
        repositorioServicios.agregar(escaleraMecanica);
        repositorioServicios.agregar(ascensor);

        RepositorioEstablecimientos repositorioEstablecimientos = new RepositorioEstablecimientos();
        repositorioEstablecimientos.agregar(medrano);
        repositorioEstablecimientos.agregar(campus);

        RepositorioEntidades repositorioEntidades = new RepositorioEntidades();
        repositorioEntidades.agregar(utn);

    }
}
