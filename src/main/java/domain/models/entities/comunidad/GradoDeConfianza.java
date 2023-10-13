package domain.models.entities.comunidad;


import domain.models.entities.converters.MedioConfiguradoAttributeConverter;
import domain.models.entities.converters.NombreGradoConfianzaAttributeConverter;
import domain.models.entities.db.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "grado_de_confianza")
public class GradoDeConfianza extends EntidadPersistente {

    @Column
    @Convert(converter = NombreGradoConfianzaAttributeConverter.class)
    public NombreGradoConfianza nombreGradoConfianza;

    @Column
    public double puntosMinimos;

    @Column
    public double puntosMaximos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grado_siguiente_id")
    public GradoDeConfianza gradoSiguiente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grado_anterior_id")
    public GradoDeConfianza gradoAnterior;      //verificar insertable y updatable ya que el test lo recomendó

    public GradoDeConfianza() {

    }
}