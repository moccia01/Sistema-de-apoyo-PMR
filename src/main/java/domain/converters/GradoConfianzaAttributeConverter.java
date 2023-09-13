package domain.converters;

import domain.comunidad.GradoDeConfianza;
import domain.comunidad.NombreGradoConfianza;
import domain.comunidad.Rol;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GradoConfianzaAttributeConverter implements AttributeConverter<GradoDeConfianza,String> {

    @Override
    public String convertToDatabaseColumn(GradoDeConfianza gradoDeConfianza) {
        return gradoDeConfianza.getNombreGradoConfianza().toString();
    }

    @Override
    public GradoDeConfianza convertToEntityAttribute(String s) {
        GradoDeConfianza gradoDeConfianza = new GradoDeConfianza();

        switch(s){
            case "NO_CONFIABLE":
                gradoDeConfianza.setNombreGradoConfianza(NombreGradoConfianza.NO_CONFIABLE);
                gradoDeConfianza.setPuntosMaximos(2);
            case "CON_RESERVAS":
                gradoDeConfianza.setNombreGradoConfianza(NombreGradoConfianza.CON_RESERVAS);
                gradoDeConfianza.setPuntosMaximos(3);
                gradoDeConfianza.setPuntosMinimos(2);
            case "CONFIABLE_NIVEL_1":
                gradoDeConfianza.setNombreGradoConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_1);
                gradoDeConfianza.setPuntosMaximos(5);
                gradoDeConfianza.setPuntosMinimos(3);
            case "CONFIABLE_NIVEL_2":
                gradoDeConfianza.setNombreGradoConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_2);
                gradoDeConfianza.setPuntosMinimos(5);
        };
        return gradoDeConfianza;
    }
}
