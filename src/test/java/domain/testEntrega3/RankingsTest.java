package domain.testEntrega3;

import domain.comunidad.Comunidad;
import domain.comunidad.Localizacion;
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
    private Entidad lineaMitre;
    private Entidad lineaTigre;
    private Servicio escaleraMecanica;
    private Establecimiento estacionRetiro;
    private Establecimiento estacionVillaBallester;
    private PrestacionDeServicio trenesArgentinos1;
    private PrestacionDeServicio trenesArgentinos;
    private Comunidad comunidadNoVidentesSM;

    @BeforeEach
    public void init(){

        escaleraMecanica = new Servicio();
        escaleraMecanica.setNombre("Escalera Mecanica");
        escaleraMecanica.setEstado(true);

        estacionRetiro = new Establecimiento();
        estacionRetiro.setNombre("Estacion retiro");
        estacionRetiro.setLocalizacion("Buenos Aires", "Comuna 1", "Av. Dr. José María Ramos Mejía 1430");
        estacionRetiro.agregarServicios(escaleraMecanica);

        estacionVillaBallester = new Establecimiento();
        estacionVillaBallester.setNombre("Estacion Villa Ballester");
        estacionVillaBallester.setLocalizacion("Buenos Aires", "General San Martin", "San Martin 4900");
        estacionVillaBallester.agregarServicios(escaleraMecanica);

        lineaMitre = new Entidad();
        lineaMitre.setNombre("Linea Mitre");
        lineaMitre.setLocalizacion("Buenos Aires");
        lineaMitre.agregarEstablecimientos(estacionRetiro, estacionVillaBallester);

        trenesArgentinos1 = new PrestacionDeServicio();
        trenesArgentinos1.setEntidad(lineaMitre);
        trenesArgentinos1.setServicio(escaleraMecanica);
        trenesArgentinos1.setEstablecimiento(estacionRetiro);

        trenesArgentinos = new PrestacionDeServicio();
        trenesArgentinos.setEntidad(lineaMitre);
        trenesArgentinos.setServicio(escaleraMecanica);
        trenesArgentinos.setEstablecimiento(estacionVillaBallester);

        comunidadNoVidentesSM = new Comunidad();
        comunidadNoVidentesSM.generarIncidente(trenesArgentinos1, "El servicio dejo de funcionar sin motivo");
        comunidadNoVidentesSM.generarIncidente(trenesArgentinos1, "El servicio dejo de funcionar sin motivo");
        comunidadNoVidentesSM.generarIncidente(trenesArgentinos, "El servicio dejo de funcionar por falta de suministro electrico");

        lineaTigre = new Entidad();
        lineaMitre.setNombre("Linea Tigre");
        lineaMitre.setLocalizacion("Buenos Aires");
        lineaMitre.agregarEstablecimientos(estacionRetiro);

        //TODO: de chusma les probe los tests y fallan porque falta agregar las comunidades al repositorio de incidentes xd
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
