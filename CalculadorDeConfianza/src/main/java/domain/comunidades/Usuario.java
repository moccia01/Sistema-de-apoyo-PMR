package domain.comunidades;

import domain.db.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario extends EntidadPersistente {

    @Column
    private double puntosDeConfianza;

    @OneToOne
    @JoinColumn(name = "grado_de_confianza_id")
    private GradoDeConfianza gradoDeConfianza;

    @ManyToOne
    @JoinColumn(name = "comunidad_id", referencedColumnName = "id")     //Esto en nuestro sistema está en miembros
    private Comunidad comunidad;


    public void actualizarPuntosDeConfianza(double puntosNuevos){
        //actualizar los puntos
        this.setPuntosDeConfianza(puntosNuevos);
        this.gradoDeConfianza.cambiarGradoSiCorresponde(this);
    }
}
