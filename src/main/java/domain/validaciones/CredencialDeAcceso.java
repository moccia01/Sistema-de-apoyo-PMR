package domain.validaciones;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class CredencialDeAcceso {
    private String nombreUsuario;
    private String contrasenia;
    private LocalDate fechaUltimoCambio;

    public CredencialDeAcceso(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public LocalDate getFechaUltimoCambio() {
        return fechaUltimoCambio;
    }

    public void setFechaUltimoCambio(LocalDate fechaUltimoCambio) {
        this.fechaUltimoCambio = fechaUltimoCambio;
    }

    public boolean esTiempoDeRotarContrasenia() {
        return ChronoUnit.MONTHS.between((this.fechaUltimoCambio), LocalDate.now()) >= 6;
    }
}

