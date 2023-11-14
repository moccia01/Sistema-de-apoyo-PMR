package domain.server.init;

import domain.models.entities.admins.AdminDePlataforma;
import domain.models.entities.builders.*;
import domain.models.entities.comunidad.*;

import domain.models.entities.converters.GradoDeConfianzaConstructor;
import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.entities.entidadesDeServicio.Establecimiento;
import domain.models.entities.entidadesDeServicio.Servicio;
import domain.models.entities.mensajes.Configuraciones.CuandoSucede;
import domain.models.entities.mensajes.Configuraciones.SinApuros;
import domain.models.entities.mensajes.Configuraciones.TiempoConfigurado;
import domain.models.entities.services.georef.ServicioGeoref;
import domain.models.entities.services.georef.entities.ListadoDeDepartamentos;
import domain.models.entities.services.georef.entities.ListadoDeMunicipios;
import domain.models.entities.services.georef.entities.ListadoDeProvincias;
import domain.models.repositorios.*;
import domain.models.repositorios.localizaciones.RepositorioDepartamentos;
import domain.models.repositorios.localizaciones.RepositorioMunicipios;
import domain.models.repositorios.localizaciones.RepositorioProvincias;
import domain.server.Server;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.IOException;

@Setter
@Getter
public class Initializer{
    private GradoDeConfianza confianzaConfiableNivel2;
    private GradoDeConfianza confianzaConfiableNivel1;
    private GradoDeConfianza confianzaConReservas;
    private GradoDeConfianza confianzaNoConfiable;

    public void init(EntityManager entityManager){
        // archivo migracion/semilla/bootstrap
        RepositorioGradosDeConfianza repositorioGradosDeConfianza = new RepositorioGradosDeConfianza();

        GradoDeConfianzaConstructor gradoDeConfianzaConstructor = new GradoDeConfianzaConstructor();
        GradoDeConfianza gradoconfiableNivel2 = repositorioGradosDeConfianza.obtenerGradoDeConfianza(entityManager, NombreGradoConfianza.CONFIABLE_NIVEL_2);

        if(gradoconfiableNivel2 == null) {
            this.confianzaNoConfiable = gradoDeConfianzaConstructor.crearGradoDeConfianzaNoConfiable();
            this.confianzaConReservas = gradoDeConfianzaConstructor.crearGradoDeConfianzaConReservas();
            this.confianzaConfiableNivel1 = gradoDeConfianzaConstructor.crearGradoDeConfianzaConfiable1();
            this.confianzaConfiableNivel2 = gradoDeConfianzaConstructor.crearGradoDeConfianzaConfiable2();

            repositorioGradosDeConfianza.agregar(this.confianzaConfiableNivel2, entityManager);
            repositorioGradosDeConfianza.agregar(this.confianzaConfiableNivel1, entityManager);
            repositorioGradosDeConfianza.agregar(this.confianzaConReservas, entityManager);
            repositorioGradosDeConfianza.agregar(this.confianzaNoConfiable, entityManager);
        } else {
            this.confianzaNoConfiable = repositorioGradosDeConfianza.obtenerGradoDeConfianza(entityManager, NombreGradoConfianza.NO_CONFIABLE);
            this.confianzaConReservas = repositorioGradosDeConfianza.obtenerGradoDeConfianza(entityManager, NombreGradoConfianza.CON_RESERVAS);
            this.confianzaConfiableNivel1 = repositorioGradosDeConfianza.obtenerGradoDeConfianza(entityManager, NombreGradoConfianza.CONFIABLE_NIVEL_1);
            this.confianzaConfiableNivel2 = repositorioGradosDeConfianza.obtenerGradoDeConfianza(entityManager, NombreGradoConfianza.CONFIABLE_NIVEL_2);
        }

/*        ServicioGeoref serviciosGeoref = ServicioGeoref.instancia("https://apis.datos.gob.ar/georef/api/");

        ListadoDeProvincias provincias = serviciosGeoref.listadoDeProvincias();
        RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
        provincias.getProvincias().forEach(repositorioProvincias::agregar);

        ListadoDeMunicipios municipios = serviciosGeoref.listadoDeMunicipios();
        RepositorioMunicipios repositorioMunicipios = new RepositorioMunicipios();
        municipios.getMunicipios().forEach(repositorioMunicipios::agregar);

        ListadoDeDepartamentos departamentos = serviciosGeoref.listadoDeDepartamentos();
        RepositorioDepartamentos repositorioDepartamentos = new RepositorioDepartamentos();
        departamentos.getDepartamentos().forEach(repositorioDepartamentos::agregar);*/

        AdminDePlataforma adminDePlataforma = new AdminDePlataforma();
        adminDePlataforma.setNombre("nisman");
        adminDePlataforma.setUsuario("nismanElFiscal");
        adminDePlataforma.setContrasenia("nisman");

        AdminDePlataforma tomas = new AdminDePlataforma();
        tomas.setNombre("admin");
        tomas.setUsuario("admin");
        tomas.setContrasenia("admin");


        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
            entityManager.persist(tomas);
            entityManager.persist(adminDePlataforma);
        tx.commit();
    }
}
