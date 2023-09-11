package domain.comunidades;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Usuario {

    private double puntosDeConfianza;
    private GradoDeConfianza gradoDeConfianza;
  //private List<Comunidad> comunidades;

    public List<Incidente> obtenerIncidentesDeComunidades(){

        return null;
    }

    public void actualizarPuntosDeConfianza(double puntosNuevos){
        //actualizar los puntos
        this.setPuntosDeConfianza(puntosNuevos);
        this.gradoDeConfianza.cambiarGradoSiCorresponde(this);
    }
}
