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
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

public class InitTest {

    @Test
    public void initTest(){
        EntityManager entityManager = Server.createEntityManagerFactory().createEntityManager();
        Initializer initializer = new Initializer();
        initializer.init(entityManager);

        //Esto es para los usuarios
        //Esta linea de abajo es para que no rompa por contexto estatico
        // ----------------------- Creación de las entidades a persistir

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
        ascensor.setNombre("ascensor");
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

        //TODO dar de alta las prestaciones asociadas a las entidades, establecimientos y servicios de arriba
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

        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
        repositorioUsuarios.agregar(tomasDAntonio, entityManager);
        repositorioUsuarios.agregar(federicoMoccia, entityManager);
        repositorioUsuarios.agregar(lucasBoldrini, entityManager);
        repositorioUsuarios.agregar(nahuGimenez, entityManager);
        repositorioUsuarios.agregar(facundoSu, entityManager);

        RepositorioComunidades repositorioComunidades = new RepositorioComunidades();
        repositorioComunidades.agregar(bosterosDesdeLaCuna,entityManager);
        repositorioComunidades.agregar(citizenDesdeLaCuna, entityManager);
        repositorioComunidades.agregar(operativosEnjoyers, entityManager);

        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
        repositorioMiembros.agregar(tomyBostero, entityManager);
        repositorioMiembros.agregar(nahuBostero, entityManager);
        repositorioMiembros.agregar(lucasBostero, entityManager);
        repositorioMiembros.agregar(fedeCitizen, entityManager);
        repositorioMiembros.agregar(facuCitizen, entityManager);
        repositorioMiembros.agregar(tomyUTNSO, entityManager);
        repositorioMiembros.agregar(fedeUTNSO, entityManager);
        repositorioMiembros.agregar(lucasUTNSO, entityManager);
        repositorioMiembros.agregar(nahuUTNSO, entityManager);

        RepositorioServicios repositorioServicios = new RepositorioServicios();
        repositorioServicios.agregar(banio, entityManager);
        repositorioServicios.agregar(escaleraMecanica, entityManager);
        repositorioServicios.agregar(ascensor, entityManager);

        RepositorioEstablecimientos repositorioEstablecimientos = new RepositorioEstablecimientos();
        repositorioEstablecimientos.agregar(medrano, entityManager);
        repositorioEstablecimientos.agregar(campus, entityManager);
        repositorioEstablecimientos.agregar(estacionMedrano, entityManager);
        repositorioEstablecimientos.agregar(estacionFedericoLacroze, entityManager);

        RepositorioEntidades repositorioEntidades = new RepositorioEntidades();
        repositorioEntidades.agregar(utn, entityManager);
        repositorioEntidades.agregar(subteLineaB, entityManager);
        
        RepositorioPrestaciones repositorioPrestaciones = new RepositorioPrestaciones();
        repositorioPrestaciones.agregar(comboMecanicaCampusUtn, entityManager);
        repositorioPrestaciones.agregar(comboMecanicaMedranoUtn, entityManager);
        repositorioPrestaciones.agregar(comboBanioCampusUtn, entityManager);
        repositorioPrestaciones.agregar(comboBanioMedranoUtn, entityManager);
        repositorioPrestaciones.agregar(comboAscensorCampus, entityManager);
        repositorioPrestaciones.agregar(comboAscensorMedrano, entityManager);
        repositorioPrestaciones.agregar(comboBanioEstacionFedericoLacroze, entityManager);
        repositorioPrestaciones.agregar(comboAscensorSubteEstacionFedericoLacroze, entityManager);
        repositorioPrestaciones.agregar(comboEscaleraMecanicaMedrano, entityManager);
        repositorioPrestaciones.agregar(comboEscaleraMecanicaFedericoLacroze, entityManager);
        repositorioPrestaciones.agregar(comboBanioEstacionMedrano, entityManager);
        repositorioPrestaciones.agregar(comboAscensorSubteEstacionMedrano, entityManager);
    }
}
