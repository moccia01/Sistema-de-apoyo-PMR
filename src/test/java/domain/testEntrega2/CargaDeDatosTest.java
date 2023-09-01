package domain.testEntrega2;

import domain.entidadesDeServicio.EntidadPrestadora;
import domain.entidadesDeServicio.OrganismoDeControl;
import domain.cargaDeDatos.CargaEntidadesPrestadoras;
import domain.cargaDeDatos.CargaOrganismosControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

public class CargaDeDatosTest {
    private CargaEntidadesPrestadoras cargadorDeEntidades;
    private CargaOrganismosControl cargadorOrganismos;
    private CargaEntidadesPrestadoras mockCargadorEntidades;
    private final String pathDatosEntidadesPrestadoras = "Entregables/ejemplo_carga_de_datos_entidades_prestadoras.csv";
    private final String pathDatosOrganismosControl = "Entregables/ejemplo_carga_de_datos_organismos_de_control.csv";

    @BeforeEach
    public void init() {
        cargadorDeEntidades = new CargaEntidadesPrestadoras(";");
        cargadorOrganismos = new CargaOrganismosControl(";");
        mockCargadorEntidades = mock(CargaEntidadesPrestadoras.class);
    }
    
    @Test
    public void cargarTodasLasEntidadesPrestadoras() {
        List<EntidadPrestadora> lista = new ArrayList<>();
        lista.add(new EntidadPrestadora("algo"));
        when(mockCargadorEntidades.cargarDatos(pathDatosEntidadesPrestadoras)).thenReturn(lista);
        Assertions.assertEquals(1, mockCargadorEntidades.cargarDatos(pathDatosEntidadesPrestadoras).size());
    }

    @Test
    public void cargarTodosLosOrganismosDeControl() {
        List<OrganismoDeControl> listaOrganismos = cargadorOrganismos.cargarDatos(pathDatosOrganismosControl);
        Assertions.assertEquals(3, listaOrganismos.size());
    }

    @Test
    public void seCarganLosNombresDelArchivoDeEntidades() {
        List<EntidadPrestadora> listaEntidades = cargadorDeEntidades.cargarDatos(pathDatosEntidadesPrestadoras);
        Assertions.assertEquals("Estado", listaEntidades.get(0).getNombreEntidad());
        Assertions.assertEquals("Banco Nacion", listaEntidades.get(1).getNombreEntidad());
        Assertions.assertEquals("Carrefour", listaEntidades.get(2).getNombreEntidad());
        Assertions.assertEquals("Cencosud", listaEntidades.get(3).getNombreEntidad());
    }

}

