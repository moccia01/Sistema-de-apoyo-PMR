package domain.validacionesTest;

import domain.validaciones.CredencialDeAcceso;
import domain.validaciones.Validador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ValidadorTest {
    private Validador validador;
    private CredencialDeAcceso credencialDeAcceso;
    @BeforeEach
    public void init(){
        validador = new Validador();
        credencialDeAcceso = new CredencialDeAcceso("usuario123");
    }

    @Test
    public void unaContraseniaQueCumpleLosRequisitosEsValida(){
        credencialDeAcceso.setContrasenia("asfasdfaSsfnm9!");
        credencialDeAcceso.setFechaUltimoCambio(LocalDate.now());

        Assertions.assertTrue(validador.validar(credencialDeAcceso));
    }

    @Test
    public void unaContraseniaDebilDelTop10000NoEsValida(){
        credencialDeAcceso.setContrasenia("qwerty");
        credencialDeAcceso.setFechaUltimoCambio(LocalDate.now());
        Assertions.assertFalse(validador.validar(credencialDeAcceso));
    }

    @Test
    public void unaContraseniaIgualAlUsuarioNoEsValida(){
        credencialDeAcceso.setContrasenia("usuario123");
        credencialDeAcceso.setFechaUltimoCambio(LocalDate.now());

        Assertions.assertFalse(validador.validar(credencialDeAcceso));
    }

    @Test
    public void unaContraseniaQueNoRespetaLongitudNoEsValida(){
        credencialDeAcceso.setContrasenia("Hola1!");
        credencialDeAcceso.setFechaUltimoCambio(LocalDate.now());

        Assertions.assertFalse(validador.validar(credencialDeAcceso));
    }

    @Test
    public void unaContraseniaQueNoRespetaComplejidadNoEsValida(){
        credencialDeAcceso.setContrasenia("Motorola");
        credencialDeAcceso.setFechaUltimoCambio(LocalDate.now());

        Assertions.assertFalse(validador.validar(credencialDeAcceso));
    }

    @Test
    public void unaContraseniaQueNoSeCambiaHaceMasDe6MesesNoEsValida(){
        credencialDeAcceso.setContrasenia("asfasdfaSsfnm9!");
        credencialDeAcceso.setFechaUltimoCambio(LocalDate.of(2022,8,15));

        Assertions.assertFalse(validador.validar(credencialDeAcceso));
    }
}
