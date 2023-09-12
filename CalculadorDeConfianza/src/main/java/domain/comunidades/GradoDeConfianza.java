package domain.comunidades;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradoDeConfianza{

    public NombreGradoConfianza nombreGradoConfianza;

    public double puntosMinimos;

    public double puntosMaximos;

    public GradoDeConfianza gradoSiguiente;

    public GradoDeConfianza gradoAnterior;      //verificar insertable y updatable ya que el test lo recomendó

    public GradoDeConfianza(NombreGradoConfianza nombreGradoConfianza) {
        this.nombreGradoConfianza = nombreGradoConfianza;
    }

    public GradoDeConfianza() {

    }

    public boolean tieneQueSubirGrado(double puntosDeConfianza) {
        return this.gradoSiguiente != null && puntosDeConfianza > this.puntosMaximos;
    }

    public boolean tieneQueBajarGrado(double puntosDeConfianza) {
        return this.gradoAnterior != null && puntosDeConfianza < this.puntosMinimos;
    }
}