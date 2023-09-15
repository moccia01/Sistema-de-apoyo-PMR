package domain.testEntrega3;

import domain.comunidad.Comunidad;
import domain.comunidad.Incidente;
import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.Establecimiento;
import domain.entidadesDeServicio.PrestacionDeServicio;
import domain.entidadesDeServicio.Servicio;
import domain.rankings.CierreIncidentes;
import domain.rankings.GeneradorDeRankings;
import domain.rankings.MayorCantidadIncidentes;
import domain.repositorios.RepositorioComunidades;
import domain.repositorios.RepositorioIncidentes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.when;

public class RankingsTest {

    private GeneradorDeRankings generador;
    private Entidad lineaMitre;
    private Entidad lineaTigre;
    private Servicio escaleraMecanica;
    private Servicio ascensor;
    private Establecimiento estacionRetiro;
    private Establecimiento estacionVillaBallester;
    private PrestacionDeServicio trenesArgentinos;
    private PrestacionDeServicio trenesArgentinos1;
    private PrestacionDeServicio trenesArgentinos2;
    private Comunidad comunidadNoVidentesSM;
    private Comunidad comunidadHipoacusicosCABA;
    private RepositorioIncidentes repoIncidentes;
    private Incidente incidente1;
    private Incidente incidente2;
    private Incidente incidente3;
    private List<Incidente> incidentes;

   @BeforeEach
    public void init(){

        escaleraMecanica = new Servicio();
        escaleraMecanica.setNombre("Escalera Mecanica");
        escaleraMecanica.setEstado(true);

        ascensor = new Servicio();
        ascensor.setNombre("Ascensor");
        ascensor.setEstado(true);

        estacionRetiro = new Establecimiento();
        estacionRetiro.setNombre("Estacion retiro");
        estacionRetiro.setLocalizacion("Buenos Aires", "Comuna 1", "Av. Dr. Jose M Ramos Mejia 1430");
        estacionRetiro.agregarServicios(escaleraMecanica);

        estacionVillaBallester = new Establecimiento();
        estacionVillaBallester.setNombre("Estacion Villa Ballester");
        estacionVillaBallester.setLocalizacion("Buenos Aires", "San Martin", "San Martin 4900");
        estacionVillaBallester.agregarServicios(escaleraMecanica);

        lineaMitre = new Entidad();
        lineaMitre.setNombre("Linea Mitre");
        lineaMitre.setLocalizacion("Buenos Aires");
        lineaMitre.agregarEstablecimientos(estacionRetiro, estacionVillaBallester);

        lineaTigre = new Entidad();
        lineaTigre.setNombre("Linea Tigre");
        lineaTigre.setLocalizacion("Buenos Aires");
        lineaTigre.agregarEstablecimientos(estacionRetiro);

        trenesArgentinos = new PrestacionDeServicio();
        trenesArgentinos.setEntidad(lineaMitre);
        trenesArgentinos.setServicio(escaleraMecanica);
        trenesArgentinos.setEstablecimiento(estacionVillaBallester);

        trenesArgentinos1 = new PrestacionDeServicio();
        trenesArgentinos1.setEntidad(lineaMitre);
        trenesArgentinos1.setServicio(escaleraMecanica);
        trenesArgentinos1.setEstablecimiento(estacionRetiro);

        trenesArgentinos2 = new PrestacionDeServicio();
        trenesArgentinos2.setEntidad(lineaTigre);
        trenesArgentinos2.setServicio(ascensor);
        trenesArgentinos2.setEstablecimiento(estacionRetiro);

        comunidadNoVidentesSM = new Comunidad();
        comunidadNoVidentesSM.generarIncidente(trenesArgentinos1, "El servicio dejo de funcionar sin motivo");
        comunidadNoVidentesSM.generarIncidente(trenesArgentinos1, "El servicio dejo de funcionar sin motivo");
        comunidadNoVidentesSM.generarIncidente(trenesArgentinos, "El servicio dejo de funcionar por falta de suministro electrico");
        comunidadNoVidentesSM.generarIncidente(trenesArgentinos2, "El ascensor dejo de funcar :(");

        incidente1 = new Incidente("Se rompio la barrera", trenesArgentinos);
        LocalDateTime fechaAperturaIncidente1 = LocalDateTime.of(2023, 3, 7, 9, 24);
        LocalDateTime fechaCierreIncidente1 = LocalDateTime.of(2023, 3, 9, 10, 54);
        incidente1.setFechaHoraApertura(fechaAperturaIncidente1);
        incidente1.setFechaHoraCierre(fechaCierreIncidente1);

        incidente2 = new Incidente("Se corto la luz", trenesArgentinos1);
        LocalDateTime fechaAperturaIncidente2 = LocalDateTime.of(2023, 3, 8, 9, 43);
        LocalDateTime fechaCierreIncidente2 = LocalDateTime.of(2023, 3, 9, 10, 54);
        incidente2.setFechaHoraApertura(fechaAperturaIncidente2);
        incidente2.setFechaHoraCierre(fechaCierreIncidente2);

        incidente3 = new Incidente("Se rompio la campana", trenesArgentinos2);
        LocalDateTime fechaAperturaIncidente3 = LocalDateTime.of(2023, 3, 10, 13, 33);
        LocalDateTime fechaCierreIncidente3 = LocalDateTime.of(2023, 3, 10, 18, 00);
        incidente3.setFechaHoraApertura(fechaAperturaIncidente3);
        incidente3.setFechaHoraCierre(fechaCierreIncidente3);

        comunidadHipoacusicosCABA = new Comunidad();
        comunidadHipoacusicosCABA.generarIncidente(trenesArgentinos, "");
        comunidadHipoacusicosCABA.generarIncidente(trenesArgentinos1, "");
        comunidadHipoacusicosCABA.generarIncidente(trenesArgentinos2, "");

        incidentes.add(incidente1);
        incidentes.add(incidente2);
        incidentes.add(incidente3);

        repoIncidentes = Mockito.mock(RepositorioIncidentes.class);
        when(repoIncidentes.obtenerIncidentes()).thenReturn(incidentes);

        generador = new GeneradorDeRankings(repoIncidentes);
    }

    @Test
    public void generarRankingMayorCantidadTest(){
        MayorCantidadIncidentes ranking1 = new MayorCantidadIncidentes();
        List<String> rankingComoDeberiaQuedar = new ArrayList<>();
        rankingComoDeberiaQuedar.add(lineaTigre.getNombre());
        rankingComoDeberiaQuedar.add(lineaMitre.getNombre());
        List<String> rankingComoQuedo = new ArrayList<>();
        rankingComoQuedo.add(generador.generarSegunCriterio(ranking1).get(0).getNombre());
        rankingComoQuedo.add(generador.generarSegunCriterio(ranking1).get(1).getNombre());
        Assertions.assertLinesMatch(rankingComoDeberiaQuedar, rankingComoQuedo);
    }

    @Test
    public void generarRankingCierreIncidentesTest(){
        CierreIncidentes ranking1 = new CierreIncidentes();

        try{
            TimeUnit.SECONDS.sleep(25);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Incidente incidenteACerrar1 = comunidadHipoacusicosCABA.getIncidentes().get(2);
        comunidadHipoacusicosCABA.cerrarIncidente(incidenteACerrar1);
        Incidente incidenteACerrar2 = comunidadNoVidentesSM.getIncidentes().get(3);
        comunidadNoVidentesSM.cerrarIncidente(incidenteACerrar2);

        List<String> rankingComoDeberiaQuedar = new ArrayList<>();
        rankingComoDeberiaQuedar.add(lineaMitre.getNombre());
        rankingComoDeberiaQuedar.add(lineaTigre.getNombre());

        List<String> rankingComoQuedo = new ArrayList<>();
        List<Entidad> ranking = generador.generarSegunCriterio(ranking1);
        rankingComoQuedo.add(ranking.get(0).getNombre());
        rankingComoQuedo.add(ranking.get(1).getNombre());

        Assertions.assertLinesMatch(rankingComoDeberiaQuedar, rankingComoQuedo);
    }

    @Test
    public void filtrarRepetidosTest(){
        MayorCantidadIncidentes ranking1 = new MayorCantidadIncidentes();

        List<Incidente> incidentes = new ArrayList<>();
        incidentes.add(incidente1);
        incidentes.add(incidente2);
        incidentes.add(incidente3);
        incidentes.add(incidente1);
        incidentes.add(incidente1);
        incidentes.add(incidente2);
        incidentes.add(incidente3);

        ranking1.filtrarRepetidos(incidentes);

        Assertions.assertEquals(3, incidentes.size());
    }
}
