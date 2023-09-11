package domain.comunidades;

public class GradoDeConfianza {
    public NombreGradoConfianza nombreGradoConfianza;
    public double puntosMinimos;
    public double puntosMaximos;
    public GradoDeConfianza gradoSiguiente;
    public GradoDeConfianza gradoAnterior;

    public GradoDeConfianza(NombreGradoConfianza nombreGradoConfianza) {
        this.nombreGradoConfianza = nombreGradoConfianza;
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