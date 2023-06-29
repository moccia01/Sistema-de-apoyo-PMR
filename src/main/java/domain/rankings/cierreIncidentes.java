package domain.rankings;

import com.twilio.rest.api.v2010.account.incomingphonenumber.Local;
import domain.comunidad.Comunidad;
import domain.comunidad.Incidente;
import domain.entidadesDeServicio.Entidad;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.text.DateFormat;

@Getter
@Setter
public class cierreIncidentes {
    private List<Comunidad> comunidades;
    private List<List<Incidente>> incidentesSemanales;
    private List<Entidad> rankingMayorPromedioCierre;

    private LocalDate fechaInicioSemana;
    private LocalDate fechaFinSemana;
    public void CambiarFechaInicioSemana(){
        fechaInicioSemana = LocalDate.now();
    }

    public void CambiarFechaFinSemana(){
        fechaFinSemana = LocalDate.now();
    }

    public void obtenerTodosLosIncidentes() {
        for (Comunidad comunidad : comunidades) {
              incidentesSemanales.add(comunidad.getIncidentes());
            }
        }
    public void filtrarXSemana(){

    }

    public void filtrarXEntidad(){


    }
       public boolean estaDentroDeLaSemana(Incidente incidente) {
        long valorDeSemana = ChronoUnit.DAYS.between(fechaInicioSemana, incidente.getFechaCierre());
        return valorDeSemana <= 7;
    }
    public int tiempoqueTarda(Incidente incidente) {
        long diasDiferencia = ChronoUnit.DAYS.between(incidente.getFechaApertura(), incidente.getFechaCierre());
        int diasDiferenciaEntero = (int) diasDiferencia;
        return diasDiferenciaEntero;
    }

    //public int obtenerFechaEntero() {
       // DateFormat formatoEntero = new SimpleDateFormat("yyyyMMdd");
       // String fechaString = formatoEntero.format();
      //  return Integer.parseInt(fechaString);
   // }



}
