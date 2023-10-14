package domain.models.entities.builders;

import domain.models.entities.builders.exceptions.GradoDeConfianzaException;
import domain.models.entities.comunidad.GradoDeConfianza;
import domain.models.entities.comunidad.NombreGradoConfianza;

public class GradoDeConfianzaBuilder {
    private GradoDeConfianza gradoDeConfianza;

    public GradoDeConfianzaBuilder(){
        this.gradoDeConfianza = new GradoDeConfianza();
    }

    public GradoDeConfianzaBuilder conNombre(NombreGradoConfianza nombreGradoConfianza){
        this.gradoDeConfianza.setNombreGradoConfianza(nombreGradoConfianza);
        return this;
    }

    public GradoDeConfianza construir(){
        if(this.gradoDeConfianza.getNombreGradoConfianza() == null){
            throw new GradoDeConfianzaException();
        }
        if(this.gradoDeConfianza.getPuntosMaximos() < 0){
            throw new GradoDeConfianzaException();
        }
        if(this.gradoDeConfianza.getPuntosMinimos() < 0){
            throw new GradoDeConfianzaException();
        }
        return gradoDeConfianza;
    }
}
