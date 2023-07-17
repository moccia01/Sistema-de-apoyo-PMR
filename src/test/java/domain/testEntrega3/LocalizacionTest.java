package domain.testEntrega3;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import domain.comunidad.Localizacion;
import domain.comunidad.Miembro;
import domain.comunidad.Rol;
import domain.comunidad.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LocalizacionTest {

    private Miembro miembroCerca;
    private Miembro miembroLejos;
    private Usuario usuarioCerca;
    private Usuario usuarioLejos;
    private Localizacion localizacion;

    @BeforeEach
    public void init(){
        usuarioCerca = new Usuario();
        usuarioLejos = new Usuario();
        localizacion = new Localizacion();
        localizacion.setProvincia("Buenos Aires");
        localizacion.setDireccion("Comuna 5","Medrano 951");

        usuarioCerca.setLocalizacion("Buenos Aires", "Comuna 5", "Medrano 1000");
        usuarioLejos.setLocalizacion("Buenos Aires", "Comuna 1", "Constitución 1328");
        miembroCerca = new Miembro(usuarioCerca, Rol.MIEMBRO);
        miembroLejos = new Miembro(usuarioLejos, Rol.MIEMBRO);
    }

    @Test
    public void unUsuarioEstaCercaDeUnaLocalizacion(){
        Assertions.assertTrue(miembroCerca.getUsuario().getLocalizacion().estaCercaDe(localizacion));
    }

    @Test
    public void unUsuarioEstaLejosDeUnaLocalizacion(){
        Assertions.assertFalse(localizacion.estaCercaDe(miembroLejos.getUsuario().getLocalizacion()));
    }

}
