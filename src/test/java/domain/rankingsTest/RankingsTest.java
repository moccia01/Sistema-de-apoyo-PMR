package domain.rankingsTest;

import domain.comunidad.Comunidad;
import domain.comunidad.Incidente;
import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.Establecimiento;
import domain.entidadesDeServicio.PrestacionDeServicio;
import domain.entidadesDeServicio.Servicio;
import domain.rankings.CierreIncidentes;
import domain.rankings.GeneradorDeRankings;
import domain.rankings.MayorCantidadIncidentes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RankingsTest {

    private GeneradorDeRankings generador;
    private Incidente incidente1;
    private Incidente incidente2;
    private Incidente incidente3;
    private Entidad lineaMitre;
    private Entidad lineaTigre;
    @BeforeEach
    public void init(){

        Servicio escaleraMecanica = new Servicio();
        escaleraMecanica.setNombre("Escalera Mecanica");
        escaleraMecanica.setEstado(true);

        Establecimiento retiro = new Establecimiento();
        retiro.setNombre("Estacion retiro");
       // retiro.setLocalizacion();
        retiro.agregarServicios(escaleraMecanica);

        Establecimiento villaBallester = new Establecimiento();
        villaBallester.setNombre("Estacion Villa Ballester");
        //villaBallester.setLocalizacion();
        villaBallester.agregarServicios(escaleraMecanica);

        lineaMitre = new Entidad();
        lineaMitre.setNombre("Linea Mitre");
        //lineaMitre.setLocalizacion();
        lineaMitre.agregarEstablecimientos(retiro, villaBallester);

        PrestacionDeServicio trenesArgentinos1 = new PrestacionDeServicio();
        trenesArgentinos1.setEntidad(lineaMitre);
        trenesArgentinos1.setServicio(escaleraMecanica);
        trenesArgentinos1.setEstablecimiento(retiro);

        PrestacionDeServicio trenesArgentinos = new PrestacionDeServicio();
        trenesArgentinos.setEntidad(lineaMitre);
        trenesArgentinos.setServicio(escaleraMecanica);
        trenesArgentinos.setEstablecimiento(villaBallester);

        Comunidad comunidadNoVidentesSM = new Comunidad();
        comunidadNoVidentesSM.generarIncidente(trenesArgentinos1, "El servicio dejo de funcionar sin motivo");
        comunidadNoVidentesSM.generarIncidente(trenesArgentinos1, "El servicio dejo de funcionar sin motivo");
        comunidadNoVidentesSM.generarIncidente(trenesArgentinos, "El servicio dejo de funcionar por falta de suministro electrico");

        lineaTigre = new Entidad();
        lineaMitre.setNombre("Linea Tigre");
        //lineaMitre.setLocalizacion();
        lineaMitre.agregarEstablecimientos(retiro);

        generador = new GeneradorDeRankings();
        generador.setIncidentes(comunidadNoVidentesSM.getIncidentes());
    }

    @Test
    public void filtrarRepetidosCada24hsTest(){ //no estoy seguro si esta bien asi, mañana revisar bien
        MayorCantidadIncidentes ranking1 = new MayorCantidadIncidentes();
        ranking1.filtrarRepetidos(generador.getIncidentes());
        Assertions.assertEquals(2, generador.getIncidentes().size());
    }

    @Test
    public void generarRankingMayorCantidadTest(){
        MayorCantidadIncidentes ranking1 = new MayorCantidadIncidentes();
        List<String> rankingComoDeberiaQuedar = new ArrayList<>();
        rankingComoDeberiaQuedar.add(lineaMitre.getNombre());
        rankingComoDeberiaQuedar.add(lineaTigre.getNombre());
        List<String> rankingComoQuedo = new ArrayList<>();
        rankingComoQuedo.add(generador.generarSegunCriterio(ranking1).get(0).getNombre());
        rankingComoQuedo.add(generador.generarSegunCriterio(ranking1).get(1).getNombre());
        Assertions.assertLinesMatch(rankingComoDeberiaQuedar, rankingComoQuedo);
    }

    @Test
    public void generarRankingCierreIncidentesTest(){
        CierreIncidentes ranking1 = new CierreIncidentes();
        List<String> rankingComoDeberiaQuedar = new ArrayList<>();
        rankingComoDeberiaQuedar.add(lineaMitre.getNombre());
        rankingComoDeberiaQuedar.add(lineaTigre.getNombre());
        List<String> rankingComoQuedo = new ArrayList<>();
        rankingComoQuedo.add(generador.generarSegunCriterio(ranking1).get(0).getNombre());
        rankingComoQuedo.add(generador.generarSegunCriterio(ranking1).get(1).getNombre());
        Assertions.assertLinesMatch(rankingComoDeberiaQuedar, rankingComoQuedo);
    }

}
