package domain.services.georef.entities;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
public class Ubicacion {

    public double lat;
    public double lon;
}
