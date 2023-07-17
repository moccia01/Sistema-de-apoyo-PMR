package domain.comunidad;
import domain.services.georef.ServicioGeoref;
import domain.services.georef.entities.*;
import domain.services.lucene.ServicioLucene;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Localizacion {
    Provincia provincia;
    Departamento departamento;
    Municipio municipio;
    Direccion direccion;

    public Localizacion(){

    }

    public void setProvincia(String provincia){
        ServicioGeoref servicioGeoref = ServicioGeoref.instancia();
        this.provincia = servicioGeoref.provincia(provincia);
    }

    public void setDireccion(String departamento, String direccion){
        ServicioGeoref servicioGeoref = ServicioGeoref.instancia();
        this.direccion = servicioGeoref.direccion(departamento, direccion);
    }

    public boolean estaCercaDe(Localizacion localizacion){
        ServicioLucene lucene = new ServicioLucene();
        double distancia = lucene.getDistance(this.direccion.getUbicacion().getLat(), this.direccion.getUbicacion().getLon(),
                localizacion.getDireccion().getUbicacion().getLat(), localizacion.getDireccion().getUbicacion().getLon());
        return distancia < 100; // estar cerca de una localizacion significa estar en un radio de 100 metros
    }
}
