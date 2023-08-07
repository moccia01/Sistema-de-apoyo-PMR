package domain.Mensajes;

import domain.comunidad.Miembro;
import domain.rankings.RepositorioComunidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificacionesPendientesSender {
    private static List<Miembro> miembros;

    public NotificacionesPendientesSender() {
        miembros = new ArrayList<>();
    }

    public static void agregarMiembros(List<Miembro> listaMiembros){
        miembros.addAll(listaMiembros);
    }

    public static void main(String[] args) {
        List<Miembro> miembros = RepositorioComunidades.obtenerMiembros();
        NotificacionesPendientesSender.agregarMiembros(miembros);
        NotificacionesPendientesSender.mandarPendientes();
    }

    public static void mandarPendientes(){
        miembros.forEach(Miembro::mandarPendientes);
    }
}
