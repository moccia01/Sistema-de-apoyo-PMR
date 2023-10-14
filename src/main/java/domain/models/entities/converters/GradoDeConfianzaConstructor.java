package domain.models.entities.converters;

import domain.models.entities.comunidad.GradoDeConfianza;
import domain.models.entities.comunidad.NombreGradoConfianza;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities.GradoDeConfianzaApi14;

public class GradoDeConfianzaConstructor {
    private static GradoDeConfianza confianzaConfiableNivel2;
    private static GradoDeConfianza confianzaConfiableNivel1;
    private static GradoDeConfianza confianzaConReservas;
    private static GradoDeConfianza confianzaNoConfiable;

    public static GradoDeConfianza crearGradoAPartirDeEnum(Integer gradoDeConfianza){
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

    public static GradoDeConfianza crearGradoAPartirDePuntosMinYMax(double puntosMinimos, double puntosMaximos) {
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

    public static GradoDeConfianza crearGradoDeConfianzaNoConfiable(){
        if(confianzaNoConfiable == null){
            confianzaNoConfiable = new GradoDeConfianza();
            confianzaNoConfiable.setNombreGradoConfianza(NombreGradoConfianza.NO_CONFIABLE);
            confianzaNoConfiable.setPuntosMaximos(2.0);
            confianzaNoConfiable.setGradoSiguiente(crearGradoDeConfianzaConReservas());
        }
        return confianzaNoConfiable;
    }


    public static GradoDeConfianza crearGradoDeConfianzaConReservas(){
        if(confianzaConReservas == null){
            confianzaConReservas = new GradoDeConfianza();
            confianzaConReservas.setNombreGradoConfianza(NombreGradoConfianza.CON_RESERVAS);
            confianzaConReservas.setPuntosMinimos(2.0);
            confianzaConReservas.setPuntosMaximos(3.0);
            confianzaConReservas.setGradoAnterior(crearGradoDeConfianzaNoConfiable());
            confianzaConReservas.setGradoSiguiente(crearGradoDeConfianzaConfiable1());
        }
        return confianzaConReservas;
    }

    public static GradoDeConfianza crearGradoDeConfianzaConfiable1() {
        if(confianzaConfiableNivel1 == null){
            confianzaConfiableNivel1 = new GradoDeConfianza();
            confianzaConfiableNivel1.setNombreGradoConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_1);
            confianzaConfiableNivel1.setPuntosMinimos(3.0);
            confianzaConfiableNivel1.setPuntosMaximos(5.0);
            confianzaConfiableNivel1.setGradoSiguiente(crearGradoDeConfianzaConfiable2());
            confianzaConfiableNivel1.setGradoAnterior(crearGradoDeConfianzaConReservas());
        }
        return confianzaConfiableNivel1;
    }

    public static GradoDeConfianza crearGradoDeConfianzaConfiable2(){
        if(confianzaConfiableNivel2 == null){
            confianzaConfiableNivel2 = new GradoDeConfianza();
            confianzaConfiableNivel2.setNombreGradoConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_2);
            confianzaConfiableNivel2.setPuntosMinimos(5.0);
            confianzaConfiableNivel2.setGradoAnterior(crearGradoDeConfianzaConfiable1());
        }
        return confianzaConfiableNivel2;
    }

    public static GradoDeConfianzaApi14 cargarGradoAPartirDePuntos(double puntos) {
        GradoDeConfianzaApi14 grado = new GradoDeConfianzaApi14();
        if(puntos <= 2){
            grado = cargarGrado(0, 2);
            return grado;
        } else if(puntos >= 2 && puntos <= 3){
            grado = cargarGrado(2, 3);
            return grado;
        }else if(puntos >=3 && puntos <= 5){
            grado = cargarGrado(3, 5);
            return grado;
        }else if(puntos >= 5){
            grado.setPuntosMinimos(5);
            return grado;
        }
        return null;
    }

    public static GradoDeConfianzaApi14 cargarGrado(double puntosMin, double puntosMax){
        GradoDeConfianzaApi14 grado = new GradoDeConfianzaApi14();
        grado.setPuntosMinimos(puntosMin);
        grado.setPuntosMaximos(puntosMax);

        return grado;
    }
}
