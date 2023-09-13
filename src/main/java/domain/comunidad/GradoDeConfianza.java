package domain.comunidad;


import domain.db.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Getter
@Setter
public class GradoDeConfianza extends EntidadPersistente {

    public NombreGradoConfianza nombreGradoConfianza;

    public double puntosMinimos;

    public double puntosMaximos;

    public GradoDeConfianza gradoSiguiente;

    public GradoDeConfianza gradoAnterior;      //verificar insertable y updatable ya que el test lo recomendó

}