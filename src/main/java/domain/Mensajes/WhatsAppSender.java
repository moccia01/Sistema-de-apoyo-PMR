package domain.Mensajes;

public interface WhatsAppSender {
    void enviarMensaje(String numero, String mensaje);
}
