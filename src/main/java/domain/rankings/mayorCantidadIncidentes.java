package domain.rankings;

import domain.comunidad.Incidente;

import java.util.List;

public class mayorCantidadIncidentes {

    List<List<Incidente>> listaIncidentes;


    public int Tiempo(Incidente){
        int TiempoAbierto = Incidente.fechaCierre - Incidente.fechaApertura;
        return TiempoAbierto;
    }

    public boolean nopaso24horas(int TiempoAbierto){
        if(TiempoAbierto < 1){
            return true;
        }
        else {return false;}
    }

    public boolean estaDentroDeLaSemanaEnLaSemana(){

   }


}
