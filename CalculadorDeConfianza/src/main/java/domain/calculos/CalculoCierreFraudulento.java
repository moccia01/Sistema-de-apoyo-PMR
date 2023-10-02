package domain.calculos;

import domain.comunidades.Incidente;
import domain.comunidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class CalculoCierreFraudulento extends CalculoGradoConfianza{

    public CalculoCierreFraudulento(double multiplicador) {
        this.multiplicador = multiplicador;
    }

    @Override
    public List<Incidente> obtenerIncidentesQueCumplen(Usuario usuario, List<Incidente> incidentes) {
        List<Incidente> incidentesCerrados = incidentes.stream().filter(i -> i.estaCerrado() && i.getUsuarioCierre() == usuario).toList();
        List<Incidente> cierresFraudulentos = new ArrayList<>();

        for(Incidente incidenteCerrado:incidentesCerrados){
            for(Incidente incidente: incidentes.stream().filter(i -> i.tieneDiferenciaDe3MinutosCon(incidenteCerrado)).toList()){
                if(incidenteCerrado.esSimilarA(incidente) && !cierresFraudulentos.contains(incidente) && incidenteCerrado != incidente){
                    cierresFraudulentos.add(incidenteCerrado);
                }
            }
        }
        return cierresFraudulentos;
    }
}
