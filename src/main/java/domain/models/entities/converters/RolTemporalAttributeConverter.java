package domain.models.entities.converters;

import domain.models.entities.comunidad.RolTemporal;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RolTemporalAttributeConverter implements AttributeConverter<RolTemporal, String> {
    @Override
    public String convertToDatabaseColumn(RolTemporal rolTemporal) {
        if(rolTemporal == null) {
            return null;
        }
        return switch (rolTemporal) {
            case AFECTADO -> "AFECTADO";
            case OBSERVADOR -> "OBSERVADOR";
        };
    }

    @Override
    public RolTemporal convertToEntityAttribute(String s) {
        if(s == null) {
            return null;
        }
        return switch(s){
            case "AFECTADO" -> RolTemporal.AFECTADO;
            case "OBSERVADOR" ->RolTemporal.OBSERVADOR;
            default -> null;
        };
    }
}
