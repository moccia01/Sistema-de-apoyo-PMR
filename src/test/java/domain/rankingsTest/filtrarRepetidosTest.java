package domain.rankingsTest;

import domain.comunidad.Incidente;
import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.Establecimiento;
import domain.entidadesDeServicio.PrestacionDeServicio;
import domain.entidadesDeServicio.Servicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class filtrarRepetidosTest {

    private Incidente incidente1;
    private Incidente incidente2;
    private Incidente incidente3;

    @BeforeEach
    public void init(){

     /*   Ejemplo 1
        CNRT (Organismo de Control)
        Trenes Argentinos (Prestadora de Servicio)
        Línea Mitre (Entidad)
                Retiro (Establecimiento)
        Escalera mecánica (Servicio)

*/

        Servicio escaleraMecanica = new Servicio();
        escaleraMecanica.setNombre("Escalera Mecanica");
        escaleraMecanica.setEstado(true);

        Establecimiento retiro = new Establecimiento();
        retiro.setNombre("Estacion retiro");
        retiro.setLocalizacion();
        retiro.agregarServicios(escaleraMecanica);

        Establecimiento villaBallester = new Establecimiento();
        villaBallester.setNombre("Estacion Villa Ballester");
        //villaBallester.setLocalizacion();
        villaBallester.agregarServicios(escaleraMecanica);

        Entidad lineaMitre = new Entidad();
        lineaMitre.setNombre("Linea Mitre");
        //lineaMitre.setLocalizacion();
        lineaMitre.agregarEstablecimientos(retiro, villaBallester);

        PrestacionDeServicio trenesArgentinos = new PrestacionDeServicio();
        trenesArgentinos.();

        incidente1 = new Incidente();
        incidente1.set

        incidente2 = new Incidente();
        incidente3 = new Incidente();

    }

    @Test
    public void filtrarRepetidosCada24hsTest(){
        List<Incidente> incidentes = new ArrayList<>();
        incidentes.add(incidente1);
        incidentes.add(incidente2);
        incidentes.add(incidente3);



    }

}
