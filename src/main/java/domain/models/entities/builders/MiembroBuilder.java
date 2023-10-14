package domain.models.entities.builders;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.Miembro;
import domain.models.entities.comunidad.Rol;
import domain.models.entities.comunidad.Usuario;

public class MiembroBuilder {
    private Miembro miembro;

    public MiembroBuilder(){
        this.miembro = new Miembro();
    }

    public MiembroBuilder conUsuario(Usuario usuario){
        this.miembro.setUsuario(usuario);
        return this;
    }

    public MiembroBuilder conRol(Rol rol){
        this.miembro.setRol(rol);
        return this;
    }

    public MiembroBuilder conComunidad(Comunidad comunidad){
        this.miembro.setComunidad(comunidad);
        return this;
    }

    public Miembro construir(){
        return miembro;
    }
}
