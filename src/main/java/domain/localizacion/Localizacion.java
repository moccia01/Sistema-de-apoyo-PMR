package domain.localizacion;
import domain.services.georef.Localizador;
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
