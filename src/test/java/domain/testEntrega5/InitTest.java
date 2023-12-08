package domain.testEntrega5;

import domain.models.entities.builders.*;
import domain.models.entities.comunidad.*;
import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.entities.entidadesDeServicio.Establecimiento;
import domain.models.entities.entidadesDeServicio.PrestacionDeServicio;
import domain.models.entities.entidadesDeServicio.Servicio;
import domain.models.entities.mensajes.Configuraciones.CuandoSucede;
import domain.models.entities.mensajes.Configuraciones.SinApuros;
import domain.models.entities.mensajes.Configuraciones.TiempoConfigurado;
import domain.models.repositorios.*;
import domain.server.Server;
import domain.server.init.Initializer;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class InitTest implements WithSimplePersistenceUnit {

    @Test
    public void initTest(){
        Server.configureEntityManagerProperties();
        EntityManager entityManager = entityManager();
        Initializer initializer = new Initializer();

        beginTransaction();
        initializer.init(entityManager);
        commitTransaction();

        TiempoConfigurado cuandoSucede = new CuandoSucede();

        UsuarioBuilder usuarioBuilder = new UsuarioBuilder();

        Usuario lucasBoldrini = usuarioBuilder.conNombre("Lucas").conApellido("Boldrini")
                .conCredencial("lBoldrini", "pepito123")
                .conMail("lboldrini@frba.utn.edu.ar").conTelefono("1567673002")
                .conTiempoConfigurado(new SinApuros())  //"SINAPUROS" o "CUANDOSUCEDE"
                .conMedioConfigurado("Email")   // "Email" o "WhatsApp"
                .conGradoDeConfianza(initializer.getConfianzaConfiableNivel1()).construir();

        Usuario tomasDAntonio = usuarioBuilder.conNombre("Tomas").conApellido("DAntonio").conMail("tdantonio@frba.utn.edu.ar")
                .conCredencial("tdantonio", "aguanteboca12").conTelefono("1234567890")
                .conTiempoConfigurado(new SinApuros()).conMedioConfigurado("WhatsApp")
                .conGradoDeConfianza(initializer.getConfianzaConfiableNivel1()).construir();


        Usuario federicoMoccia  = usuarioBuilder.conNombre("Federico").conApellido("Moccia")
                .conCredencial("fMoccia", "aguanteMetallicaNoMeImportaNada666")
                .conMail("fmoccia@frba.utn.edu.ar").conTelefono("3452654439")
                .conTiempoConfigurado(cuandoSucede)  //"SINAPUROS" o "CUANDOSUCEDE"
                .conMedioConfigurado("Email")   // "Email" o "WhatsApp"
                .conGradoDeConfianza(initializer.getConfianzaConfiableNivel1()).construir();

        Usuario nahuGimenez  = usuarioBuilder.conNombre("Nahuel").conApellido("Gimenez")
                .conCredencial("nGimenez", "hola123")
                .conMail("ngimenez@frba.utn.edu.ar").conTelefono("1528658491")
                .conTiempoConfigurado(new SinApuros())  //"SINAPUROS" o "CUANDOSUCEDE"
                .conMedioConfigurado("Email")   // "Email" o "WhatsApp"
                .conGradoDeConfianza(initializer.getConfianzaConfiableNivel1()).construir();

        Usuario facundoSu = usuarioBuilder.conNombre("Facundo").conApellido("Su")
                .conCredencial("facu123", "facu123")
                .conMail("fsu@frba.utn.edu.ar").conTelefono("1234567890")
                .conTiempoConfigurado(cuandoSucede).conMedioConfigurado("WhatsApp")
                .conGradoDeConfianza(initializer.getConfianzaConfiableNivel1()).construir();

        ComunidadBuilder comunidadBuilder = new ComunidadBuilder();

        Comunidad bosterosDesdeLaCuna = comunidadBuilder.conNombre("Comunidad No Videntes - Palermo").conGradoDeConfianza(initializer.getConfianzaConfiableNivel1()).construir();
        Comunidad citizenDesdeLaCuna = comunidadBuilder.conNombre("Comunidad Paraplejicos - San Martin").conGradoDeConfianza(initializer.getConfianzaConfiableNivel1()).construir();
        Comunidad operativosEnjoyers = comunidadBuilder.conNombre("Comunidad ELA - CABA").conGradoDeConfianza(initializer.getConfianzaConfiableNivel1()).construir();

        MiembroBuilder miembroBuilder = new MiembroBuilder();
        Miembro tomyBostero = miembroBuilder.conRol(Rol.ADMINISTRADOR).conUsuario(tomasDAntonio)
                .conComunidad(bosterosDesdeLaCuna).conRolTemporal(RolTemporal.OBSERVADOR).construir();
        Miembro nahuBostero = miembroBuilder.conRol(Rol.MIEMBRO).conUsuario(nahuGimenez)
                .conComunidad(bosterosDesdeLaCuna).conRolTemporal(RolTemporal.OBSERVADOR).construir();
        Miembro lucasBostero = miembroBuilder.conRol(Rol.ADMINISTRADOR).conUsuario(lucasBoldrini)
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
        ascensor.setNombre("Ascensor");
        ascensor.setEstado(true);

        EstablecimientoBuilder establecimientoBuilder = new EstablecimientoBuilder();
        Establecimiento campus = establecimientoBuilder.conNombre("Campus").conServicios(escaleraMecanica, banio, ascensor)
                .construir();
        Establecimiento medrano = establecimientoBuilder.conNombre("Medrano").conServicios(escaleraMecanica, banio, ascensor)
                .construir();

        Entidad utn = new Entidad();
        utn.setNombre("UTN");
        utn.setLocalizacion("Argentina");
        utn.agregarEstablecimientos(campus, medrano);

        Establecimiento estacionMedrano = establecimientoBuilder.conNombre("Estacion Medrano").conServicios(banio)
                .construir();
        Establecimiento estacionFedericoLacroze = establecimientoBuilder.conNombre("Estacion Federico Lacroze").conServicios(escaleraMecanica, banio, ascensor)
                .construir();

        Entidad subteLineaB = new Entidad();
        subteLineaB.setNombre("Subte Linea-B");
        subteLineaB.setLocalizacion("Argentina");
        subteLineaB.agregarEstablecimientos(estacionMedrano, estacionFedericoLacroze);

        PrestacionDeServicioBuilder prestacionDeServicioBuilder = new PrestacionDeServicioBuilder();
        PrestacionDeServicio comboMecanicaCampusUtn = prestacionDeServicioBuilder.conServicio(escaleraMecanica).conEstablecimiento(campus).conEntidad(utn).construir();
        PrestacionDeServicio comboMecanicaMedranoUtn = prestacionDeServicioBuilder.conServicio(escaleraMecanica).conEstablecimiento(medrano).conEntidad(utn).construir();
        PrestacionDeServicio comboBanioCampusUtn = prestacionDeServicioBuilder.conServicio(banio).conEstablecimiento(campus).conEntidad(utn).construir();

        PrestacionDeServicio comboBanioMedranoUtn = prestacionDeServicioBuilder.conServicio(banio).conEstablecimiento(medrano).conEntidad(utn).construir();
        PrestacionDeServicio comboAscensorCampus = prestacionDeServicioBuilder.conServicio(ascensor).conEstablecimiento(campus).conEntidad(utn).construir();
        PrestacionDeServicio comboAscensorMedrano = prestacionDeServicioBuilder.conServicio(ascensor).conEstablecimiento(medrano).conEntidad(utn).construir();

        PrestacionDeServicio comboBanioEstacionFedericoLacroze = prestacionDeServicioBuilder.conServicio(banio).conEstablecimiento(estacionFedericoLacroze).conEntidad(subteLineaB).construir();
        PrestacionDeServicio comboAscensorSubteEstacionFedericoLacroze = prestacionDeServicioBuilder.conServicio(ascensor).conEstablecimiento(estacionFedericoLacroze).conEntidad(subteLineaB).construir();
        PrestacionDeServicio comboEscaleraMecanicaFedericoLacroze =prestacionDeServicioBuilder.conServicio(escaleraMecanica).conEstablecimiento(estacionFedericoLacroze).conEntidad(subteLineaB).construir();

        PrestacionDeServicio comboBanioEstacionMedrano = prestacionDeServicioBuilder.conServicio(banio).conEstablecimiento(estacionMedrano).conEntidad(subteLineaB).construir();
        PrestacionDeServicio comboAscensorSubteEstacionMedrano  = prestacionDeServicioBuilder.conServicio(ascensor).conEstablecimiento(estacionMedrano).conEntidad(subteLineaB).construir();
        PrestacionDeServicio comboEscaleraMecanicaMedrano  =prestacionDeServicioBuilder.conServicio(escaleraMecanica).conEstablecimiento(estacionMedrano).conEntidad(subteLineaB).construir();


        // ----------------------- Carga a la base de datos
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        entityManager().persist(tomasDAntonio);
        entityManager().persist(federicoMoccia);
        entityManager().persist(lucasBoldrini);
        entityManager().persist(nahuGimenez);
        entityManager().persist(facundoSu);

        entityManager().persist(bosterosDesdeLaCuna);
        entityManager().persist(citizenDesdeLaCuna);
        entityManager().persist(operativosEnjoyers);

        entityManager().persist(tomyBostero);
        entityManager().persist(nahuBostero);
        entityManager().persist(lucasBostero);
        entityManager().persist(fedeCitizen);
        entityManager().persist(facuCitizen);
        entityManager().persist(tomyUTNSO);
        entityManager().persist(fedeUTNSO);
        entityManager().persist(lucasUTNSO);
        entityManager().persist(nahuUTNSO);

        entityManager().persist(banio);
        entityManager().persist(escaleraMecanica);
        entityManager().persist(ascensor);

        entityManager.persist(medrano);
        entityManager.persist(campus);
        entityManager.persist(estacionMedrano);
        entityManager.persist(estacionFedericoLacroze);

        entityManager.persist(utn);
        entityManager.persist(subteLineaB);

        entityManager().persist(comboMecanicaCampusUtn);
        entityManager().persist(comboMecanicaMedranoUtn);
        entityManager().persist(comboBanioCampusUtn);
        entityManager().persist(comboBanioMedranoUtn);
        entityManager().persist(comboAscensorCampus);
        entityManager().persist(comboAscensorMedrano);
        entityManager().persist(comboBanioEstacionFedericoLacroze);
        entityManager().persist(comboAscensorSubteEstacionFedericoLacroze);
        entityManager().persist(comboEscaleraMecanicaMedrano);
        entityManager().persist(comboEscaleraMecanicaFedericoLacroze);
        entityManager().persist(comboBanioEstacionMedrano);
        entityManager().persist(comboAscensorSubteEstacionMedrano);

        tx.commit();
    }
}
