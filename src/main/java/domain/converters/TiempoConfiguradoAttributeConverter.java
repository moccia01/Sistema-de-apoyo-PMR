package domain.converters;

import domain.mensajes.Configuraciones.CuandoSucede;
import domain.mensajes.Configuraciones.SinApuros;
import domain.mensajes.Configuraciones.TiempoConfigurado;

import javax.persistence.AttributeConverter;

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
