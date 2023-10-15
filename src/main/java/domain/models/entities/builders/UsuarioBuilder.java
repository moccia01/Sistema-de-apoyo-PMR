package domain.models.entities.builders;

import domain.models.entities.comunidad.GradoDeConfianza;
import domain.models.entities.comunidad.Interes;
import domain.models.entities.comunidad.Miembro;
import domain.models.entities.comunidad.Usuario;
import domain.models.entities.converters.GradoDeConfianzaConstructor;
import domain.models.entities.localizacion.Localizacion;
import domain.models.entities.mensajes.Configuraciones.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public UsuarioBuilder conCredencial(String nomUsuario, String contra){
        CredencialDeAccessoBuilder credencialBuilder = new CredencialDeAccessoBuilder();
        this.usuario.setCredencialDeAcceso(credencialBuilder
                                            .conNombreUsuario(nomUsuario)
                                            .conContrasenia(contra)
                                            .construir());
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

   public UsuarioBuilder conLocalizacion(String provincia, String departamento, String municipio, String direccion){
        Localizacion localizacion = new Localizacion();
        localizacion.setProvincia(provincia);
        localizacion.setDireccion(departamento, direccion);
        localizacion.setMunicipio(municipio);
        this.usuario.setLocalizacion(localizacion);
        return this;
   }

   public UsuarioBuilder conMiembros(Miembro... miembros){
        this.usuario.agregarMiembros(miembros);
        return this;
   }

   public UsuarioBuilder conTiempoConfigurado(String nombreTiempoConfig){
       SinApurosBuilder sinAapurosBuilder = new SinApurosBuilder();
       CuandoSucedeBuilder cuandoSucedeBuilder = new CuandoSucedeBuilder();


       List<LocalDateTime> tiempoConfig = new ArrayList<>();
       tiempoConfig.add(LocalDateTime.now());

       SinApuros sinAp = sinAapurosBuilder.conHorarios(tiempoConfig).construir();

        if(nombreTiempoConfig.equalsIgnoreCase("SINAPUROS")) {
            this.usuario.setTiempoConfigurado(sinAp);
        }
        else if(nombreTiempoConfig.equalsIgnoreCase("CUANDOSUCEDE")){
            this.usuario.setTiempoConfigurado(new CuandoSucede());
        }
        return this;
   }

   public UsuarioBuilder conMedioConfigurado(String medio){
        if(medio.equals("Email")){
            this.usuario.setMedioConfigurado(new MensajeEmail());
        }
        else if(medio.equals("WhatsApp")){
            this.usuario.setMedioConfigurado(new MensajeWhatsApp());
        }
        return this;
   }

   public UsuarioBuilder conGradoDeConfianza(GradoDeConfianza grado){
        this.usuario.setGradoDeConfianza(grado);
        return this;
   }

   public Usuario construir(){
        this.usuario.setPuntosDeConfianza(5);
        this.usuario.setInteres(new Interes());
        Usuario ret = this.usuario;
        this.usuario = new Usuario();
        return ret;
   }
}
