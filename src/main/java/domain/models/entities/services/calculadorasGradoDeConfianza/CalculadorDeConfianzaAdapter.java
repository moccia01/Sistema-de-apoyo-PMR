package domain.models.entities.services.calculadorasGradoDeConfianza;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.Incidente;
import domain.models.entities.comunidad.Usuario;

import java.io.IOException;
import java.util.List;

public interface CalculadorDeConfianzaAdapter {
    //TODO HACER LA INTERFAZ PARA LOS 2 CALCULADORES
    // Meter métodos en común para cada calculadorDeConfianza, impolementar con PATRON ADAPTER
    // Definir si dejamos al metodo calcularGradoConfianzaPara con
    // void calcularGradoConfianzaPara(List<Usuario> usuario, List<Comunidad> comunidad, List<Incidente> incidentes) throws IOException;
    public void calcularGradoConfianzaPara(List<Usuario> usuario, List<Comunidad> comunidad, List<Incidente> incidentes) throws IOException;

}
