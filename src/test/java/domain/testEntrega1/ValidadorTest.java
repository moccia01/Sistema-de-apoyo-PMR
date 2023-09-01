package domain.testEntrega1;

import domain.validaciones.*;
import domain.validaciones.politicasNIST.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ValidadorTest {
    private Validador validador;
    private CredencialDeAcceso credencialDeAcceso;
    private final String pathArchivoContrasenias = "Entregables/top_10000_peores_contraseñas.txt";

    @BeforeEach
    public void init(){
        validador = new Validador();
        credencialDeAcceso = new CredencialDeAcceso("usuario123");
    }

    @Test
    public void unaContraseniaQueCumpleLosRequisitosEsValida() throws IOException {
        credencialDeAcceso.setContrasenia("asfasdfaSsfnm9!");
        credencialDeAcceso.setFechaUltimoCambio(LocalDate.now());
        LectorArchivo lectorArchivo = new LectorArchivo();
        List<String> contrasenias = lectorArchivo.obtenerLineas(pathArchivoContrasenias);
        EsDebil esDebil = new EsDebil();
        esDebil.setContrasenias(contrasenias);
        validador.setValidaciones(new Longitud(), new UsaCredencialesPorDefecto(), esDebil, new Rotacion(), new TieneCaracterEspecial(), new TieneNumero(), new TieneMayuscula());

        Assertions.assertTrue(validador.validar(credencialDeAcceso));
    }

    @Test
    public void unaContraseniaDebilDelTop10000NoEsValida() throws IOException {
        credencialDeAcceso.setContrasenia("qwerty");
        LectorArchivo lectorArchivo = new LectorArchivo();
        List<String> contrasenias = lectorArchivo.obtenerLineas(pathArchivoContrasenias);
        EsDebil esDebil = new EsDebil();
        esDebil.setContrasenias(contrasenias);
        validador.setValidaciones(esDebil);

        Assertions.assertFalse(validador.validar(credencialDeAcceso));
    }

    @Test
    public void unaContraseniaIgualAlUsuarioNoEsValida(){
        credencialDeAcceso.setContrasenia("usuario123");
        UsaCredencialesPorDefecto porDefecto = new UsaCredencialesPorDefecto();
        validador.setValidaciones(porDefecto);

        Assertions.assertFalse(validador.validar(credencialDeAcceso));
    }

    @Test
    public void unaContraseniaQueNoRespetaLongitudNoEsValida(){
        credencialDeAcceso.setContrasenia("Hola1!");
        validador.setValidaciones(new Longitud());

        Assertions.assertFalse(validador.validar(credencialDeAcceso));
    }

    @Test
    public void unaContraseniaQueNoTieneCaracterEspecialNoEsValida(){
        credencialDeAcceso.setContrasenia("Motorola");
        validador.setValidaciones(new TieneCaracterEspecial());

        Assertions.assertFalse(validador.validar(credencialDeAcceso));
    }

    @Test
    public void unaContraseniaQueNoTieneNumeroNoEsValida(){
        credencialDeAcceso.setContrasenia("Motorola");
        validador.setValidaciones(new TieneNumero());

        Assertions.assertFalse(validador.validar(credencialDeAcceso));
    }

    @Test
    public void unaContraseniaQueNoTieneMayusculaNoEsValida(){
        credencialDeAcceso.setContrasenia("motorola");
        validador.setValidaciones(new TieneMayuscula());

        Assertions.assertFalse(validador.validar(credencialDeAcceso));
    }

    @Test
    public void unaContraseniaQueNoSeCambiaHaceMasDe6MesesNoEsValida(){
        credencialDeAcceso.setContrasenia("asfasdfaSsfnm9!");
        credencialDeAcceso.setFechaUltimoCambio(LocalDate.of(2022,8,15));
        validador.setValidaciones(new Rotacion());

        Assertions.assertFalse(validador.validar(credencialDeAcceso));
    }
}
