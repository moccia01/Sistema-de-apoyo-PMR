package domain.cargaDeDatosTest;

import domain.EntidadPrestadora;
import domain.OrganismoDeControl;
import domain.cargaDeDatos.CargaEntidadesPrestadoras;
import domain.cargaDeDatos.CargaOrganismosControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.*;

public class CargaDeDatosTest {
    /*   private CargaEntidadesPrestadoras cargadorDeEntidades;
       private CargaOrganismosControl cargadorOrganismos;
   */
    CargaEntidadesPrestadoras cargadorDeEntidades;
    CargaOrganismosControl cargadorOrganismos;

    @BeforeEach
    public void init() {
        //cargadorDeEntidades = new CargaEntidadesPrestadoras(";");
        //cargadorOrganismos = new CargaOrganismosControl(";");

        cargadorDeEntidades = mock(CargaEntidadesPrestadoras.class);
        cargadorOrganismos = mock(CargaOrganismosControl.class);
    }
    
    @Test
    public void cargarTodasLasEntidadesPrestadoras() {
        List<EntidadPrestadora> lista = new ArrayList<>();
        lista.add(new EntidadPrestadora("algo"));
        when(cargadorDeEntidades.cargarDatos("ejemplo_carga_de_datos_entidades_prestadoras.csv")).thenReturn(Collections.singletonList(lista));
        Assertions.assertEquals(1, cargadorDeEntidades.cargarDatos("ejemplo_carga_de_datos_entidades_prestadoras.csv").size());
    }

    @Test
    public void cargarTodosLosOrganismosDeControl() {
        List<OrganismoDeControl> listaOrganismos = cargadorOrganismos.cargarDatos("ejemplo_carga_de_datos_organismos_de_control.csv");
        Assertions.assertEquals(3, listaOrganismos.size());
    }

    @Test
    public void seCarganLosNombresDelArchivoDeEntidades() {
        List<EntidadPrestadora> listaEntidades = cargadorDeEntidades.cargarDatos("ejemplo_carga_de_datos_entidades_prestadoras.csv");

        Assertions.assertEquals("Estado", listaEntidades.get(0).getNombreEntidad());
        Assertions.assertEquals("Banco Nacion", listaEntidades.get(1).getNombreEntidad());
        Assertions.assertEquals("Carrefour", listaEntidades.get(2).getNombreEntidad());
        Assertions.assertEquals("Cencosud", listaEntidades.get(3).getNombreEntidad());

    }
}


