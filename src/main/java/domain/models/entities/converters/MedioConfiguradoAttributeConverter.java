package domain.models.entities.converters;

import domain.models.entities.mensajes.Configuraciones.MedioConfigurado;
import domain.models.entities.mensajes.Configuraciones.MensajeEmail;
import domain.models.entities.mensajes.Configuraciones.MensajeWhatsApp;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MedioConfiguradoAttributeConverter implements AttributeConverter<MedioConfigurado, String> {

    @Override
    public String convertToDatabaseColumn(MedioConfigurado medioConfigurado) {
        if (medioConfigurado == null) {
            return null;
        }
        return medioConfigurado.nombre();
    }

    @Override
    public MedioConfigurado convertToEntityAttribute(String s) {
        if(s == null) {
            return null;
        }
        return switch (s) {
            case "MensajeWhatsapp" -> new MensajeWhatsApp();
            case "MensajeEmail" -> new MensajeEmail();
            default -> null;
        };
    }
}
