package domain.models.entities.builders;

import domain.models.entities.comunidad.GradoDeConfianza;
import domain.models.entities.comunidad.Miembro;
import domain.models.entities.comunidad.Usuario;
import domain.models.entities.converters.GradoDeConfianzaConverter;
import domain.models.entities.localizacion.Localizacion;
import domain.models.entities.mensajes.Configuraciones.MedioConfigurado;
import domain.models.entities.mensajes.Configuraciones.TiempoConfigurado;
import domain.models.entities.validaciones.CredencialDeAcceso;

public class UsuarioBuilder {
    private Usuario usuario;

    public UsuarioBuilder(){
        this.usuario = new Usuario();
    }

    public UsuarioBuilder conNombre(String nombre){
        this.usuario.setNombre(nombre);
        return this;
    }

    public UsuarioBuilder conApellido(String apellido){
        this.usuario.setApellido(apellido);
        return this;
    }
//TODO modificar este metodo para pasarle usuario y contra y que haga la credencial
    public UsuarioBuilder conCredencial(CredencialDeAcceso credencial){
        CredencialDeAcceso credencialDeAcceso = new CredencialDeAccess
        this.usuario.setCredencialDeAcceso(credencial);
        return this;
    }

    public UsuarioBuilder conMail(String mail){
        this.usuario.setMail(mail);
        return this;
    }

    public UsuarioBuilder conTelefono(String telefono){
        this.usuario.setTelefono(telefono);
        return this;
    }

   public UsuarioBuilder conLocalizacion(Localizacion localizacion){
        this.usuario.setLocalizacion(localizacion);
        return this;
   }

   public UsuarioBuilder conMiembros(Miembro... miembros){
        this.usuario.agregarMiembros(miembros);
        return this;
   }

   public UsuarioBuilder conTiempoConfigurado(TiempoConfigurado tiempo){
        this.usuario.setTiempoConfigurado(tiempo);
        return this;
   }

   public UsuarioBuilder conMedioConfigurado(MedioConfigurado medio){
        this.usuario.setMedioConfigurado(medio);
        return this;
   }

   public UsuarioBuilder conGradoDeConfianza(GradoDeConfianza grado){
        GradoDeConfianzaConverter converter = new GradoDeConfianzaConverter();
        grado = converter.crearGradoDeConfianzaConfiable1();
        this.usuario.setGradoDeConfianza(grado);
        return this;
   }

   public Usuario construir(){
        return usuario;
   }
}
