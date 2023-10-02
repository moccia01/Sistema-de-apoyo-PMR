package domain.testEntrega3;

import domain.models.entities.localizacion.Localizacion;
import domain.models.entities.comunidad.Miembro;
import domain.models.entities.comunidad.Usuario;
import org.junit.jupiter.api.Assertions;
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
        miembroCerca = new Miembro();
        miembroCerca.setUsuario(usuarioCerca);
        miembroLejos = new Miembro();
        miembroLejos.setUsuario(usuarioLejos);
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
