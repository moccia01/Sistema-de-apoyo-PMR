package domain.models.entities.builders;

import domain.models.entities.validaciones.CredencialDeAcceso;

import java.time.LocalDate;

public class CredencialDeAccessoBuilder {
    private CredencialDeAcceso credencial;

    public CredencialDeAccessoBuilder(){
        this.credencial = new CredencialDeAcceso();
    }

   public CredencialDeAccessoBuilder conNombreUsuario(String nombreUsuario){
        this.credencial.setNombreUsuario(nombreUsuario);
        return this;
   }

   public CredencialDeAccessoBuilder conContraseña(String contraseña){
        this.credencial.setContrasenia(contraseña);
        return this;
   }

   public CredencialDeAccessoBuilder conFecha(){
        this.credencial.setFechaUltimoCambio(LocalDate.now());
        return this;
   }

   public CredencialDeAcceso construir(){
        return credencial;
   }
}
