package domain.models.entities.mensajes;

public interface WhatsAppSender {
    void enviarMensaje(String numero, String mensaje);
}
