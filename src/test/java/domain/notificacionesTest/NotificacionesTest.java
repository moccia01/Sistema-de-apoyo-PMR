package domain.notificacionesTest;

import domain.Mensajes.Configuraciones.MedioConfigurado;
import domain.Mensajes.Configuraciones.MensajeEmail;
import domain.Mensajes.Configuraciones.MensajeWhatsApp;
import domain.Mensajes.Configuraciones.TareaProgramada;
import domain.comunidad.Miembro;
import domain.comunidad.Rol;
import domain.comunidad.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NotificacionesTest {

    private MensajeWhatsApp mockMensajeWhatApp;
    private Miembro miembro;

    @BeforeEach
    public void init(){
        miembro = new Miembro(new Usuario(), Rol.MIEMBRO);
    }

    @Test
    public void enviarMail(){
        MensajeEmail enviarMail = new MensajeEmail();
        Usuario usuario = new Usuario();
        usuario.setMail("federico21433@hotmail.com");

        miembro.setUsuario(usuario);

        String mensaje = "buenas esto es una notificacion";

        enviarMail.enviarNotificacion(miembro,mensaje);
    }

    @Test
    public void enviarWhatsApp(){
        MensajeWhatsApp msjWhatsapp = new MensajeWhatsApp();
        //mockMensajeWhatApp = mock(MensajeWhatsApp.class);
        miembro.getUsuario().setNumero("+5491123497049");
        String mensaje = "Notificacion";

        //when(mockMensajeWhatApp.enviarNotificacion(miembro, mensaje)).thenReturn();
        //TODO mockear esto
        msjWhatsapp.enviarNotificacion(miembro, mensaje);
    }
}
