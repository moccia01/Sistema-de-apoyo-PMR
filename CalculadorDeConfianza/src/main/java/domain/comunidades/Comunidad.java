package domain.comunidades;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
public class Comunidad{
    private long id;
    private double puntosDeConfianza;
    private GradoDeConfianza gradoDeConfianza;
    private List<Usuario> usuarios;

    public Comunidad() {
        this.usuarios = new ArrayList<>();
    }

    public void agregarUsuarios(Usuario ... usuarios){
        Collections.addAll(this.usuarios, usuarios);
    }

    public void actualizarPuntosDeConfianza(double puntosNuevos){
        this.setPuntosDeConfianza(puntosNuevos);
        if (this.gradoDeConfianza.tieneQueSubirGrado(this.puntosDeConfianza)) {
            this.gradoDeConfianza = this.gradoDeConfianza.getGradoSiguiente();
        } else if (this.gradoDeConfianza.tieneQueBajarGrado(this.puntosDeConfianza)) {
            this.gradoDeConfianza = this.gradoDeConfianza.getGradoAnterior();
        }
    }
}
