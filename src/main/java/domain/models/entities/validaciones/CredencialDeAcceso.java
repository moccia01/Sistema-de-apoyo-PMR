package domain.models.entities.validaciones;

import domain.models.entities.db.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Entity
@Table(name = "credencial")
public class CredencialDeAcceso extends EntidadPersistente {

    @Column(name="nombreUsuario")
    private String nombreUsuario;

    @Column(name="contrasenia")
    private String contrasenia;

    @Column(name="fechaUltimoCambio", columnDefinition = "DATE")
    private LocalDate fechaUltimoCambio;

    public CredencialDeAcceso(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public CredencialDeAcceso(){
    }

    public boolean esTiempoDeRotarContrasenia() {
        return ChronoUnit.MONTHS.between((this.fechaUltimoCambio), LocalDate.now()) >= 6;
    }
}

