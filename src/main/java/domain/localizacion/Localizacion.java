package domain.localizacion;
import domain.services.georef.Localizador;
import domain.services.georef.ServicioGeoref;
import domain.services.georef.entities.*;
import domain.services.lucene.ServicioLucene;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Embeddable
public class Localizacion {

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

    public boolean estaCercaDe(Localizacion localizacion){
        ServicioLucene lucene = new ServicioLucene();
        double distancia = lucene.getDistance(this.direccion.getUbicacion().getLat(), this.direccion.getUbicacion().getLon(),
                localizacion.getDireccion().getUbicacion().getLat(), localizacion.getDireccion().getUbicacion().getLon());
        return distancia < 100; // estar cerca de una localizacion significa estar en un radio de 100 metros
    }
}
