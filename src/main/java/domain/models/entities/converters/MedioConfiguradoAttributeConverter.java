package domain.models.entities.converters;

import domain.models.entities.mensajes.Configuraciones.MedioConfigurado;
import domain.models.entities.mensajes.Configuraciones.MensajeEmail;
import domain.models.entities.mensajes.Configuraciones.MensajeWhatsApp;

import javax.persistence.AttributeConverter;

public class MedioConfiguradoAttributeConverter implements AttributeConverter<MedioConfigurado, String> {

    @Override
    public String convertToDatabaseColumn(MedioConfigurado medioConfigurado) {
        return medioConfigurado.toString();
    }

    @Override
    public MedioConfigurado convertToEntityAttribute(String s) {
        return switch (s) {
            case "MensajeWhatsapp" -> new MensajeWhatsApp();
            case "MensajeEmail" -> new MensajeEmail();
            default -> null;
        };
    }
}
