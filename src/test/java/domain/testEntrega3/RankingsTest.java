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
import domain.rankings.RepositorioIncidentes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

/*
        incidente1 = new Incidente("Se rompio la barrera", trenesArgentinos);
        LocalDate fechaAperturaIncidente1 = LocalDate.of(2023, 3, 7);
        LocalDate fechaCierreIncidente1 = LocalDate.of(2023, 3, 9);
        LocalTime horarioAperturaIncidente1 = LocalTime.of(9, 24);
        LocalTime horarioCierreIncidente1 = LocalTime.of(10, 54);
        incidente1.setFechaApertura(fechaAperturaIncidente1);
        incidente1.setFechaCierre(fechaCierreIncidente1);
        incidente1.setHorarioApertura(horarioAperturaIncidente1);
        incidente1.setHorarioApertura(horarioCierreIncidente1);

        incidente2 = new Incidente("Se corto la luz", trenesArgentinos1);
        LocalDate fechaAperturaIncidente2 = LocalDate.of(2023, 3, 8);
        LocalDate fechaCierreIncidente2 = LocalDate.of(2023, 3, 9);
        LocalTime horarioAperturaIncidente2 = LocalTime.of(9, 43);
        LocalTime horarioCierreIncidente2 = LocalTime.of(10, 54);
        incidente2.setFechaApertura(fechaAperturaIncidente2);
        incidente2.setFechaCierre(fechaCierreIncidente2);
        incidente2.setHorarioApertura(horarioAperturaIncidente2);
        incidente2.setHorarioApertura(horarioCierreIncidente2);

        incidente3 = new Incidente("Se rompio la campana", trenesArgentinos2);
        LocalDate fechaAperturaIncidente3 = LocalDate.of(2023, 3, 10);
        LocalDate fechaCierreIncidente3 = LocalDate.of(2023, 3, 10);
        LocalTime horarioAperturaIncidente3 = LocalTime.of(13, 33);
        LocalTime horarioCierreIncidente3 = LocalTime.of(18, 00);
        incidente3.setFechaApertura(fechaAperturaIncidente3);
        incidente3.setFechaCierre(fechaCierreIncidente3);
        incidente3.setHorarioApertura(horarioAperturaIncidente3);
        incidente3.setHorarioApertura(horarioCierreIncidente3);
*/

        comunidadHipoacusicosCABA = new Comunidad();
        comunidadHipoacusicosCABA.generarIncidente(trenesArgentinos, "");
        comunidadHipoacusicosCABA.generarIncidente(trenesArgentinos1, "");
        comunidadHipoacusicosCABA.generarIncidente(trenesArgentinos2, "");

        repoIncidentes = new RepositorioIncidentes();
        repoIncidentes.agregarComunidades(comunidadNoVidentesSM, comunidadHipoacusicosCABA);

        generador = new GeneradorDeRankings();
    }

    @Test
    public void filtrarRepetidosCada24hsTest(){
        MayorCantidadIncidentes ranking1 = new MayorCantidadIncidentes();
        ranking1.filtrarRepetidos(generador.getIncidentes());
        Assertions.assertEquals(3, generador.getIncidentes().size());
    }

    @Test
    public void generarRankingMayorCantidadTest(){
        MayorCantidadIncidentes ranking1 = new MayorCantidadIncidentes();
        LocalDateTime fechaComienzoSemana =  LocalDateTime.of(2023, 7, 17, 0, 0, 0);
        LocalDateTime fechaFinSemana =  LocalDateTime.of(2023, 7, 23, 23, 59, 59);
        ranking1.setFechaComienzoSemana(fechaComienzoSemana);
        ranking1.setFechaFinSemana(fechaFinSemana);
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
        LocalDateTime fechaComienzoSemana =  LocalDateTime.of(2023, 7, 17, 0, 0, 0);
        LocalDateTime fechaFinSemana =  LocalDateTime.of(2023, 7, 23, 23, 59, 59);
        ranking1.setFechaComienzoSemana(fechaComienzoSemana);
        ranking1.setFechaFinSemana(fechaFinSemana);

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
        rankingComoDeberiaQuedar.add(lineaTigre.getNombre());
        rankingComoDeberiaQuedar.add(lineaMitre.getNombre());

        List<String> rankingComoQuedo = new ArrayList<>();
        List<Entidad> ranking = generador.generarSegunCriterio(ranking1);
        rankingComoQuedo.add(ranking.get(0).getNombre());
        rankingComoQuedo.add(ranking.get(1).getNombre());

        Assertions.assertLinesMatch(rankingComoDeberiaQuedar, rankingComoQuedo);
    }


    @Test
    public void fdasadf(){
       /*MayorCantidadIncidentes ranking1 = new MayorCantidadIncidentes();
       List<Incidente> incidentes = new ArrayList<>();
       Incidente incidente = new Incidente("fadsadfsfdas", trenesArgentinos);
       Incidente incidente1 = new Incidente("fadsadfsfdas", trenesArgentinos);
       Incidente incidente2 = new Incidente("jkdfkjlak", trenesArgentinos);
       LocalDate fechaAperturaIncidente = LocalDate.of(2023, 7, 18);
       LocalDate fechaAperturaIncidente1 = LocalDate.of(2023, 7, 12);
       ranking1.setFechaFinSemana(fechaAperturaIncidente1);
       System.out.println(LocalDate.now().isAfter(ranking1.getFechaFinSemana()));
       */
        LocalDateTime fechaComienzoSemana =  LocalDateTime.of(2023, 7, 17, 0, 0, 0);
        LocalDateTime fechaFinSemana =  LocalDateTime.of(2023, 7, 23, 23, 59, 59);
        LocalDateTime fecha = LocalDateTime.of(2023, 7, 23, 18, 59, 59);

        Assertions.assertTrue(ChronoUnit.HOURS.between(fechaFinSemana, fecha) < 6);
        Assertions.assertEquals(Math.abs(ChronoUnit.HOURS.between(fechaFinSemana, fechaComienzoSemana)), 167);

    }


}