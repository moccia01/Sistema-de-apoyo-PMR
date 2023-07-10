package domain.comunidad;
import domain.services.georef.entities.Centroide;
import domain.services.lucene.ServicioLucene;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Localizacion {
    Centroide centroide;
    String provincia;
    String departamento;
    String municipio;

    public Localizacion(String provincia) {
        this.provincia = provincia;
    }

    public boolean estaCercaDe(Localizacion localizacion){
        ServicioLucene lucene = new ServicioLucene();
        double distancia = lucene.getDistance(this.centroide.lat, this.centroide.lon, localizacion.centroide.lat, localizacion.centroide.lon);
        return distancia < 100; // estar cerca de una localizacion significa estar en un radio de 100 metros
    }
}
