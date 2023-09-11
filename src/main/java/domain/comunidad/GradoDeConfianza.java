package domain.comunidad;

import domain.db.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "gradoDeConfianza")
public class GradoDeConfianza extends EntidadPersistente {

    @Enumerated(EnumType.STRING)
    @Column(name = "nombreGradoConfianza")
    public NombreGradoConfianza nombreGradoConfianza;

    @Column
    public double puntosMinimos;

    @Column
    public double puntosMaximos;

    @OneToOne
    //TODO: Chequear estos 2 atributos y chequear completamente gradoDeConfianza Si hay que persistirlo
    @JoinColumn(name = "grado_de_confianza_id", insertable = false, updatable = false)     //Chequear si es grado_de_confianza_id
    public GradoDeConfianza gradoSiguiente;

    @OneToOne
    @JoinColumn(name = "grado_de_confianza_id", insertable = false, updatable = false)     //Chequear si es grado_de_confianza_id
    public GradoDeConfianza gradoAnterior;
}
