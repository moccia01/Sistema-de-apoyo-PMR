package domain.services.twilio;

public interface WhatsAppSender {
    void enviarMensaje(String numero, String mensaje);
}
