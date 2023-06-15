package domain.cargaDeDatosTest;

import domain.EntidadPrestadora;
import domain.OrganismoDeControl;
import domain.cargaDeDatos.CargaEntidadesPrestadoras;
import domain.cargaDeDatos.CargaOrganismosControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CargaDeDatosTest {
    private CargaEntidadesPrestadoras cargadorDeDatos;
    private CargaOrganismosControl cargadorOrganismos;

    @BeforeEach
    public void init(){
        cargadorDeDatos = new CargaEntidadesPrestadoras();
        cargadorOrganismos = new CargaOrganismosControl();
    }

    @Test
    public void cargarTodasLasEntidades(){
        cargadorDeDatos.cargarDatosEntidadesPrestadoras("ejemplo_carga_de_datos_entidades_prestadoras.csv");
        List<EntidadPrestadora> listaEntidades = cargadorDeDatos.getEntidadesPrestadoras();

        Assertions.assertEquals(4, listaEntidades.size());
    }

    @Test
    public void cargarTodosLosOrganismosDeControl(){
        cargadorOrganismos.cargarDatosOrganismosDeControl("ejemplo_carga_de_datos_organismos_de_control.csv");
        List<OrganismoDeControl> listaOrganismos = cargadorOrganismos.getOrganismosDeControl();

        Assertions.assertEquals(3, listaOrganismos.size());
    }

    @Test
    public void seCarganLosNombresDelArchivoDeEntidades(){
        cargadorDeDatos.cargarDatosEntidadesPrestadoras("ejemplo_carga_de_datos_entidades_prestadoras.csv");
        List<EntidadPrestadora> listaEntidades = cargadorDeDatos.getEntidadesPrestadoras();

        Assertions.assertEquals("Estado", listaEntidades.get(0).getNombreEntidad());
        Assertions.assertEquals("Banco Nacion", listaEntidades.get(1).getNombreEntidad());
        Assertions.assertEquals("Carrefour", listaEntidades.get(2).getNombreEntidad());
        Assertions.assertEquals("Cencosud", listaEntidades.get(3).getNombreEntidad());
    }
}
