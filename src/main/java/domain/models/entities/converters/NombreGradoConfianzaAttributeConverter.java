package domain.models.entities.converters;

import domain.models.entities.comunidad.NombreGradoConfianza;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class NombreGradoConfianzaAttributeConverter implements AttributeConverter<NombreGradoConfianza, String> {
    @Override
    public String convertToDatabaseColumn(NombreGradoConfianza nombreGradoConfianza) {
        return nombreGradoConfianza.toString();
    }

    @Override
    public NombreGradoConfianza convertToEntityAttribute(String s) {
        return switch (s) {
            case "NO_CONFIABLE" -> NombreGradoConfianza.NO_CONFIABLE;
            case "CON_RESERVAS" -> NombreGradoConfianza.CON_RESERVAS;
            case "CONFIABLE_NIVEL_1" -> NombreGradoConfianza.CONFIABLE_NIVEL_1;
            case "CONFIABLE_NIVEL_2" -> NombreGradoConfianza.CONFIABLE_NIVEL_2;
            default -> null;
        };
    }
}
