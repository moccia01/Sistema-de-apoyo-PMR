package domain.models.entities.localizacion;
import domain.models.entities.services.georef.Localizador;
import domain.models.entities.services.georef.ServicioGeoref;
import domain.models.entities.services.georef.entities.Departamento;
import domain.models.entities.services.georef.entities.Direccion;
import domain.models.entities.services.georef.entities.Municipio;
import domain.models.entities.services.georef.entities.Provincia;
import domain.models.entities.services.lucene.ServicioLucene;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.IOException;

@Getter
@Setter
@Embeddable
public class Localizacion {

    // WARNING: medio raro tener referencias a entities en un embeddable
    @ManyToOne
    @JoinColumn(name = "provincia_id", referencedColumnName = "id")
    Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "departamento_id", referencedColumnName = "id")
    Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "municipio_id", referencedColumnName = "id")
    Municipio municipio;

    @Embedded
    Direccion direccion;

    public void setProvincia(String provincia){
        Localizador localizador = ServicioGeoref.instancia();
        this.provincia = localizador.provincia(provincia);
    }

    public void setDireccion(String departamento, String direccion){
        Localizador localizador = ServicioGeoref.instancia();
        this.direccion = localizador.direccion(departamento, direccion);
    }

    public void setMunicipio(String municipio) throws IOException {
        Localizador localizador = ServicioGeoref.instancia();
        this.municipio = localizador.municipio(municipio);
    }

    public boolean estaCercaDe(Localizacion localizacion){
        ServicioLucene lucene = new ServicioLucene();
        double distancia = lucene.getDistance(this.direccion.getUbicacion().getLat(), this.direccion.getUbicacion().getLon(),
                localizacion.getDireccion().getUbicacion().getLat(), localizacion.getDireccion().getUbicacion().getLon());
        return distancia < 100; // estar cerca de una localizacion significa estar en un radio de 100 metros
    }
}
