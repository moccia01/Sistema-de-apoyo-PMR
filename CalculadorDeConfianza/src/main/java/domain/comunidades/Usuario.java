package domain.comunidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {
    private long id;
    private double puntosDeConfianza;
    private GradoDeConfianza gradoDeConfianza;

    public Usuario() {

    }

    public void actualizarPuntosDeConfianza(double puntosNuevos){
        //actualizar los puntos
        this.setPuntosDeConfianza(puntosNuevos);
        if (this.gradoDeConfianza.tieneQueSubirGrado(this.puntosDeConfianza)) {
            this.gradoDeConfianza = this.gradoDeConfianza.getGradoSiguiente();
        } else if (this.gradoDeConfianza.tieneQueBajarGrado(this.puntosDeConfianza)) {
            this.gradoDeConfianza = this.gradoDeConfianza.getGradoAnterior();
        }
    }
}
