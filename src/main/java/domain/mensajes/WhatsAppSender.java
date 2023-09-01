package domain.mensajes;

public interface WhatsAppSender {
    void enviarMensaje(String numero, String mensaje);
}
