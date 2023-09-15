package domain.testsServicio;

import domain.CalculadoraGradoApp;
import domain.calculos.CalculadorGradoConfianza;
import domain.calculos.CalculoAperturaCierreCorrecto;
import domain.calculos.CalculoAperturaFraudulenta;
import domain.calculos.CalculoCierreFraudulento;
import domain.comunidades.*;
import domain.controllers.ActualizacionGradoConfianzaController;
import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.Establecimiento;
import domain.entidadesDeServicio.PrestacionDeServicio;
import domain.entidadesDeServicio.Servicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TestsServicio {
    private Usuario nahu;
    private Usuario fede;
    private Usuario tomas;
    private Comunidad comunidad;
    private Comunidad operativosEnjoyers;
    private Entidad utn;
    private Establecimiento medrano;
    private Establecimiento campus;
    private Servicio escalera;
    private Servicio banio;
    private PrestacionDeServicio escaleraMedrano;
    private PrestacionDeServicio banioCampus;
    private PrestacionDeServicio escaleraCampus;
    private Incidente escaleraMedranoRota;
    private Incidente escaleraMedranoRota2;
    private Incidente escaleraCampusRota;
    private Incidente banioCampusRoto;
    private GradoDeConfianza confianzaConfiableNivel2;
    private GradoDeConfianza confianzaConfiableNivel1;
    private GradoDeConfianza confianzaConReservas;
    private GradoDeConfianza confianzaNoConfiable;
    private List<Usuario> usuarios;
    private List<Comunidad> comunidades;
    private List<Incidente> incidentes;
    private CalculadorGradoConfianza calculadorGradoConfianza;
    private CalculoCierreFraudulento calculoCierreFraudulento;
    private CalculoAperturaFraudulenta calculoAperturaFraudulenta;
    private CalculoAperturaCierreCorrecto calculoAperturaCierreCorrecto;

    @BeforeEach
    public void init(){
        nahu = new Usuario();
        fede = new Usuario();
        tomas = new Usuario();

        confianzaConfiableNivel2 = new GradoDeConfianza();
        confianzaConfiableNivel1 = new GradoDeConfianza();
        confianzaConReservas = new GradoDeConfianza();
        confianzaNoConfiable = new GradoDeConfianza();

        confianzaConfiableNivel2.setNombreGradoConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_2);
        confianzaConfiableNivel2.setPuntosMinimos(5.0);
        confianzaConfiableNivel2.setGradoAnterior(confianzaConfiableNivel1);

        confianzaConfiableNivel1.setNombreGradoConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_1);
        confianzaConfiableNivel1.setPuntosMinimos(3.5);
        confianzaConfiableNivel1.setPuntosMaximos(5.0);
        confianzaConfiableNivel1.setGradoSiguiente(confianzaConfiableNivel2);
        confianzaConfiableNivel1.setGradoAnterior(confianzaConReservas);

        confianzaConReservas.setNombreGradoConfianza(NombreGradoConfianza.CON_RESERVAS);
        confianzaConReservas.setPuntosMinimos(2.0);
        confianzaConReservas.setPuntosMaximos(3.0);
        confianzaConReservas.setGradoAnterior(confianzaNoConfiable);
        confianzaConReservas.setGradoSiguiente(confianzaConfiableNivel1);

        confianzaNoConfiable.setNombreGradoConfianza(NombreGradoConfianza.NO_CONFIABLE);
        confianzaNoConfiable.setPuntosMaximos(2.0);
        confianzaNoConfiable.setGradoSiguiente(confianzaConReservas);

        fede.setPuntosDeConfianza(5.0);
        fede.setGradoDeConfianza(confianzaConfiableNivel2);
        fede.setId(1);

        tomas.setPuntosDeConfianza(5.0);
        tomas.setGradoDeConfianza(confianzaConfiableNivel2);
        tomas.setId(2);

        nahu.setPuntosDeConfianza(5.0);
        nahu.setGradoDeConfianza(confianzaConfiableNivel2);
        nahu.setId(3);

        comunidad = new Comunidad();
        comunidad.agregarUsuarios(nahu, fede);
        comunidad.setPuntosDeConfianza(5.0);
        comunidad.setGradoDeConfianza(confianzaConfiableNivel2);

        operativosEnjoyers = new Comunidad();
        operativosEnjoyers.agregarUsuarios(tomas, fede);
        operativosEnjoyers.setPuntosDeConfianza(5.0);
        operativosEnjoyers.setGradoDeConfianza(confianzaConfiableNivel2);

        utn = new Entidad();
        utn.setNombre("UTN");

        escalera = new Servicio();
        escalera.setNombre("Escalera");

        banio = new Servicio();
        banio.setNombre("Baño");

        medrano = new Establecimiento();
        medrano.setNombre("Medrano");

        campus = new Establecimiento();
        campus.setNombre("Campus");

        escaleraMedrano = new PrestacionDeServicio();
        escaleraMedrano.setEntidad(utn);
        escaleraMedrano.setEstablecimiento(medrano);
        escaleraMedrano.setServicio(escalera);

        banioCampus = new PrestacionDeServicio();
        banioCampus.setEntidad(utn);
        banioCampus.setEstablecimiento(campus);
        banioCampus.setServicio(banio);

        escaleraCampus = new PrestacionDeServicio();
        escaleraCampus.setEntidad(utn);
        escaleraCampus.setEstablecimiento(campus);
        escaleraCampus.setServicio(escalera);

        escaleraMedranoRota = new Incidente();
        escaleraMedranoRota.setFechaApertura(LocalDateTime.of(2023, 9, 13,11,13));
        escaleraMedranoRota.setFechaCierre(LocalDateTime.of(2023, 9, 13,11, 15));
        escaleraMedranoRota.setUsuarioApertura(fede);
        escaleraMedranoRota.setUsuarioCierre(tomas);
        escaleraMedranoRota.setPrestacionDeServicio(escaleraMedrano);
        escaleraMedranoRota.setEstado(true);
        escaleraMedranoRota.setId(1);

        escaleraMedranoRota2 = new Incidente();
        escaleraMedranoRota2.setFechaApertura(LocalDateTime.of(2023, 9, 13, 11, 17));
        escaleraMedranoRota2.setUsuarioApertura(nahu);
        escaleraMedranoRota2.setPrestacionDeServicio(escaleraMedrano);
        escaleraMedranoRota2.setEstado(false);
        escaleraMedranoRota2.setId(2);

        banioCampusRoto = new Incidente();
        banioCampusRoto.setFechaApertura(LocalDateTime.of(2023, 9, 12, 2, 3));
        banioCampusRoto.setFechaCierre(LocalDateTime.of(2023,9,10, 2, 5));
        banioCampusRoto.setUsuarioApertura(tomas);
        banioCampusRoto.setUsuarioCierre(nahu);
        banioCampusRoto.setPrestacionDeServicio(banioCampus);
        banioCampusRoto.setEstado(true);

        escaleraCampusRota = new Incidente();
        escaleraCampusRota.setFechaApertura(LocalDateTime.of(2023, 9, 12, 2, 0));
        escaleraCampusRota.setFechaCierre(LocalDateTime.of(2023, 9, 12, 2, 5));
        escaleraCampusRota.setUsuarioApertura(nahu);
        escaleraCampusRota.setUsuarioCierre(tomas);
        escaleraCampusRota.setPrestacionDeServicio(escaleraCampus);
        escaleraCampusRota.setEstado(true);

        calculadorGradoConfianza = new CalculadorGradoConfianza();
        calculoAperturaFraudulenta = new CalculoAperturaFraudulenta(-0.2);
        calculoCierreFraudulento = new CalculoCierreFraudulento(-0.2);
        calculoAperturaCierreCorrecto = new CalculoAperturaCierreCorrecto(0.5);
        calculadorGradoConfianza.agregarCalculosGradoConfianza(calculoAperturaFraudulenta, calculoCierreFraudulento, calculoAperturaCierreCorrecto);

        usuarios = new ArrayList<>();
        comunidades = new ArrayList<>();
        incidentes = new ArrayList<>();

    }

    @Test
    public void seDescuentanLosPuntosNecesariosSiHayAperturaFraudulenta(){
        usuarios.add(tomas);
        usuarios.add(fede);
        comunidades.add(comunidad);
        incidentes.add(escaleraMedranoRota);


        calculadorGradoConfianza.actualizarPuntosDeConfianza(usuarios,comunidades,incidentes);

        // hizo una apertura fraudulenta asi que se descuentan 0.2
        Assertions.assertEquals(4.8, tomas.getPuntosDeConfianza());

    }

    @Test
    public void seDescuentanLosPuntosNecesariosSiHayCierreFraudulento(){
        usuarios.add(fede);
        usuarios.add(nahu);
        usuarios.add(tomas);
        comunidades.add(comunidad);
        incidentes.add(escaleraMedranoRota);
        incidentes.add(escaleraMedranoRota2);

        calculadorGradoConfianza.actualizarPuntosDeConfianza(usuarios,comunidades,incidentes);

        // se le restan 0.2 por la apertura fraudulenta y 0.2 por el cierre fraudulento
        Assertions.assertEquals(4.6, tomas.getPuntosDeConfianza());
    }

    @Test
    public void seSumanLosPuntosNecesariosPorCadaAperturaYCierreCorrecto(){
        usuarios.add(fede);
        usuarios.add(nahu);
        comunidades.add(comunidad);
        incidentes.add(escaleraCampusRota);
        incidentes.add(escaleraMedranoRota);
        incidentes.add(escaleraMedranoRota2);

        calculadorGradoConfianza.actualizarPuntosDeConfianza(usuarios,comunidades,incidentes);

        // se le suman 0.5 porque hizo una apertura correcta
        Assertions.assertEquals(5.5, fede.getPuntosDeConfianza());
        // se le suman 1.0 porque hizo una apertura correcta
        Assertions.assertEquals(6.0, nahu.getPuntosDeConfianza());
    }

    @Test
    public void seDisminuyeGradoDeConfianzaSiLosPuntosSonMenoresALosMinimos(){
        usuarios.add(fede);
        usuarios.add(nahu);
        usuarios.add(tomas);
        comunidades.add(comunidad);
        incidentes.add(escaleraMedranoRota);
        incidentes.add(escaleraMedranoRota2);

        calculadorGradoConfianza.actualizarPuntosDeConfianza(usuarios,comunidades,incidentes);

        // se le restan 0.2 por la apertura fraudulenta y 0.2 por el cierre fraudulento
        Assertions.assertEquals(4.6, tomas.getPuntosDeConfianza());
        Assertions.assertEquals(confianzaConfiableNivel1.getNombreGradoConfianza(), tomas.getGradoDeConfianza().getNombreGradoConfianza());
    }

    @Test
    public void seSumaGradoDeConfianzaSiLosPuntosSonSuperioresALosMaximos(){
        usuarios.add(nahu);
        comunidades.add(comunidad);
        incidentes.add(escaleraCampusRota);
        incidentes.add(escaleraMedranoRota);
        incidentes.add(escaleraMedranoRota2);

        nahu.setPuntosDeConfianza(2.5);
        nahu.setGradoDeConfianza(confianzaConReservas);

        calculadorGradoConfianza.actualizarPuntosDeConfianza(usuarios,comunidades,incidentes);

        // se le suman 1.0 porque hizo una apertura correcta
        Assertions.assertEquals(3.5, nahu.getPuntosDeConfianza());
        Assertions.assertEquals(confianzaConfiableNivel1.getNombreGradoConfianza(), nahu.getGradoDeConfianza().getNombreGradoConfianza());
    }

    @Test
    public void seActualizanLosGradosDeConfianzaDeLasComunidadesCorrectamente(){
        usuarios.add(nahu);
        usuarios.add(fede);
        comunidades.add(comunidad);
        incidentes.add(escaleraCampusRota);
        incidentes.add(escaleraMedranoRota);
        incidentes.add(escaleraMedranoRota2);

        nahu.setPuntosDeConfianza(2.5);
        nahu.setGradoDeConfianza(confianzaConReservas);

        calculadorGradoConfianza.actualizarPuntosDeConfianza(usuarios,comunidades,incidentes);

        Assertions.assertEquals(confianzaConfiableNivel2.getNombreGradoConfianza(), fede.getGradoDeConfianza().getNombreGradoConfianza());
        Assertions.assertEquals(confianzaConfiableNivel1.getNombreGradoConfianza(), nahu.getGradoDeConfianza().getNombreGradoConfianza());
    }
}