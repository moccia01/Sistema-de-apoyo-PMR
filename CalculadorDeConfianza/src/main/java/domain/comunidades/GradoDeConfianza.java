package domain.comunidades;

import domain.db.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

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

    @OneToOne          //TODO: Chequear estos 2 atributos y chequear completamente gradoDeConfianza Si hay que persistirlo
    @JoinColumn(name = "grado_de_confianza_id")     //Chequear si es grado_de_confianza_id
    public GradoDeConfianza gradoSiguiente;

    @OneToOne
    @JoinColumn(name = "grado_de_confianza_id")     //Chequear si es grado_de_confianza_id
    public GradoDeConfianza gradoAnterior;

    public GradoDeConfianza(NombreGradoConfianza nombreGradoConfianza) {
        this.nombreGradoConfianza = nombreGradoConfianza;
    }

    public GradoDeConfianza() {

    }

    public boolean tieneQueSubirGrado(Usuario usuario) {
        return this.gradoSiguiente != null && usuario.getPuntosDeConfianza() > this.puntosMaximos;
    }

    public boolean tieneQueBajarGrado(Usuario usuario) {
        return this.gradoAnterior != null && usuario.getPuntosDeConfianza() < this.puntosMinimos;
    }

    public void subirGrado(Usuario usuario) {
        usuario.setGradoDeConfianza(gradoSiguiente);
    }

    public void bajarGrado(Usuario usuario) {
        usuario.setGradoDeConfianza(gradoAnterior);
    }

    public void cambiarGradoSiCorresponde(Usuario usuario) {
        if (this.tieneQueSubirGrado(usuario)) {
            this.subirGrado(usuario);
        } else if (this.tieneQueBajarGrado(usuario)) {
            this.bajarGrado(usuario);
        }
    }
}