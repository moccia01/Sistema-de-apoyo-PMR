package domain.services.twilio;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class ServicioTwilio implements WhatsAppSender{
    public static final String ACCOUNT_SID = "ACee5e089639f8884b915415a574a3f1b4";
    public static final String AUTH_TOKEN = "b39b4b926e75bdd5e3c54ba080eab56e";
    private static final String NUMERO_TWILIO = "whatsapp:+14155238886";

    public ServicioTwilio() {
    }

    @Override
    public void enviarMensaje(String numero, String mensaje) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:" + numero),
                new com.twilio.type.PhoneNumber(NUMERO_TWILIO),
                mensaje)
                    .create();
        System.out.println(message.getSid());
    }
}
