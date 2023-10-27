package domain.models.entities.converters;

import domain.models.entities.mensajes.Configuraciones.CuandoSucede;
import domain.models.entities.mensajes.Configuraciones.SinApuros;
import domain.models.entities.mensajes.Configuraciones.TiempoConfigurado;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TiempoConfiguradoAttributeConverter implements AttributeConverter<TiempoConfigurado, String> {
    @Override
    public String convertToDatabaseColumn(TiempoConfigurado tiempoConfigurado) {
        if(tiempoConfigurado == null) {
            return null;
        }
        return tiempoConfigurado.toString();
    }

    @Override
    public TiempoConfigurado convertToEntityAttribute(String s) {
        if(s == null) {
            return null;
        }
        return switch (s) {
            case "SinApuros" -> new SinApuros();
            case "CuandoSucede" -> new CuandoSucede();
            default -> null;
        };
    }
}
