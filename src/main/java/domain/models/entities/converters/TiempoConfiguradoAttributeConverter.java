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
        return tiempoConfigurado.toString();
    }

    @Override
    public TiempoConfigurado convertToEntityAttribute(String s) {
        return switch (s) {
            case "SinApuros" -> new SinApuros();
            case "CuandoSucede" -> new CuandoSucede();
            default -> null;
        };
    }
}
