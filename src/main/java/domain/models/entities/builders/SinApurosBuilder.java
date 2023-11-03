package domain.models.entities.builders;

import domain.models.entities.mensajes.Configuraciones.SinApuros;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class SinApurosBuilder {
    public SinApuros sinApuros;

    public SinApurosBuilder(){
        this.sinApuros = new SinApuros();
    }

    public SinApurosBuilder conHorarios(List<LocalDateTime> horarios){
        this.sinApuros.setHorarios(horarios);
        return this;
    }

    public SinApuros construir(){
        return sinApuros;
    }

}
