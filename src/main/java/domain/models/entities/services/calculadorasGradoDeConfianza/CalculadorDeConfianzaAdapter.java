package domain.models.entities.services.calculadorasGradoDeConfianza;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.Incidente;
import domain.models.entities.comunidad.Usuario;

import java.util.List;

public interface CalculadorDeConfianzaAdapter {
        //TODO HACER LA INTERFAZ PARA LOS 2 CALCULADORES
    // Metodo calcular grado de confianza
    // Meter métodos en común para cada calculadorDeConfianza, impolementar con PATRON ADAPTER, luego agarrar
    // y cambiarle este nombre ya que es un desastre, tal vez CalculadorDeConfianzaAdapter
    Usuario calcularGradoConfianzaParaUn(Usuario usuario, List<Incidente> incidentes);
    Comunidad calcularGradoConfianzaParaUna(Comunidad comunidad, List<Incidente> incidentes);
}
