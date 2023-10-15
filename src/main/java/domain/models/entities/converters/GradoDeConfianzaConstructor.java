package domain.models.entities.converters;

import domain.models.entities.builders.GradoDeConfianzaBuilder;
import domain.models.entities.comunidad.GradoDeConfianza;
import domain.models.entities.comunidad.NombreGradoConfianza;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities.GradoDeConfianzaApi14;

public class GradoDeConfianzaConstructor {

    private GradoDeConfianzaBuilder gradoDeConfianzaBuilder;

    public GradoDeConfianzaConstructor() {
        this.gradoDeConfianzaBuilder = new GradoDeConfianzaBuilder();
    }

    public GradoDeConfianza crearGradoAPartirDeEnum(Integer gradoDeConfianza){
        switch (gradoDeConfianza) {
            case 0 -> {
                return crearGradoDeConfianzaNoConfiable();
            }
            case 1 -> {
                return crearGradoDeConfianzaConReservas();
            }
            case 2 -> {
                return crearGradoDeConfianzaConfiable1();
            }
            case 3 -> {
                return crearGradoDeConfianzaConfiable2();
            }
        }
        return null;
    }

    public GradoDeConfianza crearGradoAPartirDePuntosMinYMax(double puntosMinimos, double puntosMaximos) {
        if (puntosMinimos == 0 && puntosMaximos == 2) {
            return crearGradoDeConfianzaNoConfiable();
        } else if (puntosMinimos == 2 && puntosMaximos == 3) {
            return crearGradoDeConfianzaConReservas();
        } else if (puntosMinimos == 3 && puntosMaximos == 5) {
            return crearGradoDeConfianzaConfiable1();
        } else if (puntosMinimos > 5) {
            return crearGradoDeConfianzaConfiable2();
        }
        return null;
    }

    public GradoDeConfianza crearGradoDeConfianzaNoConfiable(){

        return gradoDeConfianzaBuilder.generarGradoDeConfianza(NombreGradoConfianza.NO_CONFIABLE);
    }

    public GradoDeConfianza crearGradoDeConfianzaConReservas(){
        return gradoDeConfianzaBuilder.generarGradoDeConfianza(NombreGradoConfianza.CON_RESERVAS);
    }

    public GradoDeConfianza crearGradoDeConfianzaConfiable1() {
        return gradoDeConfianzaBuilder.generarGradoDeConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_1);
    }

    public GradoDeConfianza crearGradoDeConfianzaConfiable2(){
        return gradoDeConfianzaBuilder.generarGradoDeConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_2);
    }

    public GradoDeConfianzaApi14 cargarGradoAPartirDePuntos(double puntos) {
        GradoDeConfianzaApi14 grado = new GradoDeConfianzaApi14();
        if(puntos <= 2){
            grado = cargarGrado(0, 2);
        } else if(puntos >= 2 && puntos <= 3){
            grado = cargarGrado(2, 3);
        }else if(puntos >= 3 && puntos <= 5){
            grado = cargarGrado(3, 5);
        }else if(puntos >= 5){
            grado.setPuntosMinimos(5);
        }
        return grado;
    }

    public GradoDeConfianzaApi14 cargarGrado(double puntosMin, double puntosMax){
        GradoDeConfianzaApi14 grado = new GradoDeConfianzaApi14();
        grado.setPuntosMinimos(puntosMin);
        grado.setPuntosMaximos(puntosMax);

        return grado;
    }
}
