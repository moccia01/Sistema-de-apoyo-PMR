package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.Incidente;
import domain.models.entities.comunidad.Usuario;
import domain.models.entities.services.calculadorasGradoDeConfianza.CalculadorDeConfianzaAdapter;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities.ComunidadApi14;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities.PayloadDTOApi14;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities.UsuarioApi14;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.GradoDeConfianza5Service;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities.ComunidadApi5;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities.UsuarioApi5;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ServicioCalculadoraGradoDeConfianza14 implements CalculadorDeConfianzaAdapter {

    private static ServicioCalculadoraGradoDeConfianza14 instancia = null;

    private final String urlApi = "aca iría la url si el servidor estuviera corriendo";

    private Retrofit retrofit;

    private ServicioCalculadoraGradoDeConfianza14() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioCalculadoraGradoDeConfianza14 instancia(){
        if(instancia== null){
            instancia = new ServicioCalculadoraGradoDeConfianza14();
        }
        return instancia;
    }

    public PayloadDTOApi14 jsonDevuelto(PayloadDTOApi14 jsonComunidadUsuario) throws IOException{
        GradoDeConfianza14Service gradoDeConfianza14Service = this.retrofit.create((GradoDeConfianza14Service.class));
        Call<PayloadDTOApi14> requestGradoConfianzaUsuario = gradoDeConfianza14Service.usuarioComunidad(jsonComunidadUsuario);
        Response<PayloadDTOApi14> responseGradoConfianzaUsuario = requestGradoConfianzaUsuario.execute();
        return responseGradoConfianzaUsuario.body();
    }



    public Comunidad calcularGradoConfianzaParaUna(Comunidad comunidad, List<Incidente> incidentes) throws IOException  {
        return null;
    }

    @Override
    public void calcularGradoConfianzaPara(Usuario usuario, Comunidad comunidad, List<Incidente> incidentes) throws IOException {
        PayloadDTOApi14 json = new PayloadDTOApi14();

        json.cargar(usuario, comunidad, incidentes);

        json = jsonDevuelto(json);

        usuario.setPuntosDeConfianza(json.getUsuarios().get(1).getPuntosDeConfianza());
        // usuario.setGradoDeConfianza(json.getUsuarios().get(1).getGradoDeConfianza());
        // TODO crear un gradoDeConfianza14Converter para transformar el int/enum que nos devuelven
        // a la clase GradoDeConfianza que queremos, seteando en la misma los puntosMinimos, maximos
        // y todo lo que requiera, haciendo un switch ya que por cada case, se crearia uno nuevo con
        // con el nombre y demas

        // TODO definir si la comunidad debería tener puntosDeConfianza y gradoDeConfianza
        // Si no requiere los puntos, definir que hacer con eso
        // Si no requiere el gradoDeConfianza, definir si lo ignoramos o no

        // comunidad.setPuntosDeConfianza(json.getComunidades().get(1).getPuntosDeConfianza());
        // comunidad.setGradoDeConfianza(json.getComunidades().get(1).getGradoDeConfianza());

    }

}
