package domain.models.entities.converters;

import domain.models.entities.comunidad.Rol;

import javax.persistence.AttributeConverter;

public class RolAttributeConverter implements AttributeConverter<Rol, String> {
    @Override
    public String convertToDatabaseColumn(Rol rol) {
        return switch (rol) {
            case ADMINISTRADOR -> "ADMINISTRADOR";
            case MIEMBRO -> "MIEMBRO";
        };
    }

    @Override
    public Rol convertToEntityAttribute(String s) {
        Rol rol = null;

        switch(s){
            case "ADMINISTRADOR":
                rol = rol.ADMINISTRADOR;
            case "MIEMBRO":
                rol = rol.MIEMBRO;
        };
        return rol;
    }
}
