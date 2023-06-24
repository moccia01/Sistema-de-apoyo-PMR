package domain.rankings;

import domain.comunidad.Comunidad;
import domain.comunidad.Incidente;
import domain.entidadesDeServicio.Entidad;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class cierreIncidentes {
    public List<Comunidad> comunidades;
    public List<List<Incidente>> incidentesSemanales;
    public List<Entidad> rankingMayorPromedioCierre;
    public LocalDate fechaInicioSemana;

    public void CambiarFechaInicioSemana(){
        if(ChronoUnit.DAYS.between(fechaInicioSemana, LocalDate.now()) == 7){
            fechaInicioSemana = LocalDate.now();
        }
    }
    public boolean estaDentroDeLaSemana(Incidente incidente) {
        return if(ChronoUnit.DAYS.between(fechaInicioSemana, incidente.getFechaCierre()) )
    }

    public int tiempo(Incidente incidente){
        TiempoQueTardo = incidente.getFechaCierre() - incidente.getFechaApertura();
    }


}
