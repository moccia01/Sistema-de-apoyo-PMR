package domain.models.entities.builders;

import domain.models.entities.comunidad.GradoDeConfianza;
import domain.models.entities.comunidad.Miembro;
import domain.models.entities.comunidad.Usuario;
import domain.models.entities.converters.GradoDeConfianzaConstructor;
import domain.models.entities.localizacion.Localizacion;
import domain.models.entities.mensajes.Configuraciones.*;

import java.io.IOException;
import java.time.LocalTime;
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
                                            .conContraseña(contra)
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

   public UsuarioBuilder conLocalizacion(String provincia, String departamento, String municipio, String direccion) throws IOException {
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

       List<LocalTime> tiempoConfig = Arrays.asList(LocalTime.of(12, 30, 0), LocalTime.of(18, 45, 0));

       SinApuros sinAp = sinAapurosBuilder.conHorarios(tiempoConfig).construir();

        if(nombreTiempoConfig.toUpperCase() == "SINAPUROS") {
            this.usuario.setTiempoConfigurado(sinAp);
        }
        else if(nombreTiempoConfig.toUpperCase() == "CUANDOSUCEDE"){
            this.usuario.setTiempoConfigurado(new CuandoSucede());
        }
        return this;
   }

   public UsuarioBuilder comMedioConfigurado(String medio){
        if(medio == "Email"){
            this.usuario.setMedioConfigurado(new MensajeEmail());
        }
        else if(medio == "WhatsApp"){
            this.usuario.setMedioConfigurado(new MensajeWhatsApp());
        }
        return this;
   }

   public UsuarioBuilder conGradoDeConfianza(){
        GradoDeConfianza grado;
        GradoDeConfianzaConstructor converter = new GradoDeConfianzaConstructor();
        grado = converter.crearGradoDeConfianzaConfiable1();
        this.usuario.setGradoDeConfianza(grado);
        return this;
   }

   public Usuario construir(){
        return usuario;
   }
}
