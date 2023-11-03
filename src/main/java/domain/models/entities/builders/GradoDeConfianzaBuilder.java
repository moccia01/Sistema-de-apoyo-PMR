package domain.models.entities.builders;

import domain.models.entities.builders.exceptions.GradoDeConfianzaException;
import domain.models.entities.comunidad.GradoDeConfianza;
import domain.models.entities.comunidad.NombreGradoConfianza;

public class GradoDeConfianzaBuilder {
    public GradoDeConfianza gradoDeConfianza;
    private GradoDeConfianza confianzaConfiableNivel2;
    private GradoDeConfianza confianzaConfiableNivel1;
    private GradoDeConfianza confianzaConReservas;
    private GradoDeConfianza confianzaNoConfiable;

    public GradoDeConfianzaBuilder(){
        gradoDeConfianza = new GradoDeConfianza();
        confianzaConfiableNivel2 = new GradoDeConfianza();
        confianzaConfiableNivel1 = new GradoDeConfianza();
        confianzaConReservas = new GradoDeConfianza();
        confianzaNoConfiable = new GradoDeConfianza();
        this.inicializarGrados();
    }


    public void inicializarGrados(){
        confianzaConfiableNivel2.setNombreGradoConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_2);
        confianzaConfiableNivel2.setPuntosMinimos(5.0);
        confianzaConfiableNivel2.setGradoAnterior(confianzaConfiableNivel1);

        confianzaConfiableNivel1.setNombreGradoConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_1);
        confianzaConfiableNivel1.setPuntosMinimos(3.5);
        confianzaConfiableNivel1.setPuntosMaximos(5.0);
        confianzaConfiableNivel1.setGradoSiguiente(confianzaConfiableNivel2);
        confianzaConfiableNivel1.setGradoAnterior(confianzaConReservas);

        confianzaConReservas.setNombreGradoConfianza(NombreGradoConfianza.CON_RESERVAS);
        confianzaConReservas.setPuntosMinimos(2.0);
        confianzaConReservas.setPuntosMaximos(3.0);
        confianzaConReservas.setGradoAnterior(confianzaNoConfiable);
        confianzaConReservas.setGradoSiguiente(confianzaConfiableNivel1);

        confianzaNoConfiable.setNombreGradoConfianza(NombreGradoConfianza.NO_CONFIABLE);
        confianzaNoConfiable.setPuntosMaximos(2.0);
        confianzaNoConfiable.setGradoSiguiente(confianzaConReservas);
    }

    public GradoDeConfianza construir(){
        if(this.gradoDeConfianza.getNombreGradoConfianza() == null){
            throw new GradoDeConfianzaException();
        }
        if(this.gradoDeConfianza.getPuntosMaximos() < 0){
            throw new GradoDeConfianzaException();
        }
        if(this.gradoDeConfianza.getPuntosMinimos() < 0) {
            throw new GradoDeConfianzaException();
        }
        return gradoDeConfianza;
    }

    public GradoDeConfianza generarGradoDeConfianza(NombreGradoConfianza nombreGradoConfianza) {
        switch (nombreGradoConfianza){
            case NO_CONFIABLE -> {
                return confianzaNoConfiable;
            }
            case CON_RESERVAS -> {
                return confianzaConReservas;
            }
            case CONFIABLE_NIVEL_1 -> {
                return confianzaConfiableNivel1;
            }
            case CONFIABLE_NIVEL_2 -> {
                return confianzaConfiableNivel2;
            }
        }

        return null;
    }
}
