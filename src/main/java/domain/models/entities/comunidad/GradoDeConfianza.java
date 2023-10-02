package domain.models.entities.comunidad;


import domain.models.entities.db.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradoDeConfianza extends EntidadPersistente {

    public NombreGradoConfianza nombreGradoConfianza;

    public double puntosMinimos;

    public double puntosMaximos;

    public GradoDeConfianza gradoSiguiente;

    public GradoDeConfianza gradoAnterior;      //verificar insertable y updatable ya que el test lo recomendó

}