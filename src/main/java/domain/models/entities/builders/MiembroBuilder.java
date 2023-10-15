package domain.models.entities.builders;

import domain.models.entities.comunidad.*;

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

    public MiembroBuilder conRolTemporal(RolTemporal rolTemporal) {
        this.miembro.setRolTemporal(rolTemporal);
        return this;
    }

    public Miembro construir(){
        Miembro ret = this.miembro;
        this.miembro = new Miembro();
        return ret;
    }
}
