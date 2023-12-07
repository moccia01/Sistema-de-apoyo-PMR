package domain.server.init;

import domain.models.entities.admins.AdminDePlataforma;
import domain.models.entities.comunidad.*;
import domain.models.entities.converters.GradoDeConfianzaConstructor;
import domain.models.entities.services.georef.ServicioGeoref;
import domain.models.entities.services.georef.entities.ListadoDeDepartamentos;
import domain.models.entities.services.georef.entities.ListadoDeMunicipios;
import domain.models.entities.services.georef.entities.ListadoDeProvincias;
import domain.models.repositorios.*;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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

            EntityTransaction tx = entityManager.getTransaction();

            tx.begin();
            entityManager.persist(this.confianzaConfiableNivel2);
            entityManager.persist(this.confianzaConfiableNivel1);
            entityManager.persist(this.confianzaConReservas);
            entityManager.persist(this.confianzaNoConfiable);
            tx.commit();
        } else {
            this.confianzaNoConfiable = repositorioGradosDeConfianza.obtenerGradoDeConfianza(entityManager, NombreGradoConfianza.NO_CONFIABLE);
            this.confianzaConReservas = repositorioGradosDeConfianza.obtenerGradoDeConfianza(entityManager, NombreGradoConfianza.CON_RESERVAS);
            this.confianzaConfiableNivel1 = repositorioGradosDeConfianza.obtenerGradoDeConfianza(entityManager, NombreGradoConfianza.CONFIABLE_NIVEL_1);
            this.confianzaConfiableNivel2 = repositorioGradosDeConfianza.obtenerGradoDeConfianza(entityManager, NombreGradoConfianza.CONFIABLE_NIVEL_2);
        }

        ServicioGeoref serviciosGeoref = ServicioGeoref.instancia("https://apis.datos.gob.ar/georef/api/");

        ListadoDeProvincias provincias = serviciosGeoref.listadoDeProvincias();
        ListadoDeMunicipios municipios = serviciosGeoref.listadoDeMunicipios();
        ListadoDeDepartamentos departamentos = serviciosGeoref.listadoDeDepartamentos();


        AdminDePlataforma adminDePlataforma = new AdminDePlataforma();
        adminDePlataforma.setNombre("nisman");
        adminDePlataforma.setUsuario("nismanElFiscal");
        adminDePlataforma.setContrasenia("nisman");

        AdminDePlataforma tomas = new AdminDePlataforma();
        tomas.setNombre("admin");
        tomas.setUsuario("Admin");
        tomas.setContrasenia("admin");


        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
            provincias.getProvincias().forEach(entityManager::persist);
            municipios.getMunicipios().forEach(entityManager::persist);
            departamentos.getDepartamentos().forEach(entityManager::persist);

            entityManager.persist(tomas);
            entityManager.persist(adminDePlataforma);
        tx.commit();
    }
}
