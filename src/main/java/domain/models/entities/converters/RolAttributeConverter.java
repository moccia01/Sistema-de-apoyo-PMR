package domain.models.entities.converters;

import domain.models.entities.comunidad.Rol;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RolAttributeConverter implements AttributeConverter<Rol, String> {
    @Override
    public String convertToDatabaseColumn(Rol rol) {
        if(rol == null) {
            return null;
        }
        return switch (rol) {
            case ADMINISTRADOR -> "ADMINISTRADOR";
            case MIEMBRO -> "MIEMBRO";
        };
    }

    @Override
    public Rol convertToEntityAttribute(String s) {
        if(s == null) {
            return null;
        }
        return switch (s) {
            case "ADMINISTRADOR" -> Rol.ADMINISTRADOR;
            case "MIEMBRO" -> Rol.MIEMBRO;
            default -> null;
        };
    }
}
