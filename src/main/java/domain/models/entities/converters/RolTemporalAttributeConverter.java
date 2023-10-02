package domain.models.entities.converters;

import domain.models.entities.comunidad.RolTemporal;

import javax.persistence.AttributeConverter;

public class RolTemporalAttributeConverter implements AttributeConverter<RolTemporal, String> {
    @Override
    public String convertToDatabaseColumn(RolTemporal rolTemporal) {
        return switch (rolTemporal) {
            case AFECTADO -> "AFECTADO";
            case OBSERVADOR -> "OBSERVADOR";
        };
    }

    @Override
    public RolTemporal convertToEntityAttribute(String s) {
        RolTemporal rolTemporal = null;

        switch(s){
            case "AFECTADO":
                 rolTemporal = RolTemporal.AFECTADO;
            case "OBSERVADOR":
                 rolTemporal = RolTemporal.OBSERVADOR;
        };
        return rolTemporal;
    }
}
