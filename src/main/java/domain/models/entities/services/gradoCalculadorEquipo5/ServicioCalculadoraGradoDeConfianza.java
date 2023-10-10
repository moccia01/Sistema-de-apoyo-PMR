package domain.models.entities.services.gradoCalculadorEquipo5;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.services.gradoCalculadorEquipo5.entities.ComunidadApi;
import domain.models.entities.services.gradoCalculadorEquipo5.entities.UsuarioApi;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioCalculadoraGradoDeConfianza {
    //"https://github.com/a-sandoval/servicio-entrega4-tpa-grupo5/tree/master/ServicioCalculadorGradoDeConfianza"
    private static ServicioCalculadoraGradoDeConfianza instancia = null;
    private final String urlApi = "https://raw.githubusercontent.com/gradoDeConfianza/"; //RARO
    private Retrofit retrofit;

    private ServicioCalculadoraGradoDeConfianza() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static ServicioCalculadoraGradoDeConfianza instancia(){
        if(instancia== null){
            instancia = new ServicioCalculadoraGradoDeConfianza();
        }
        return instancia;
    }

    public ComunidadApi comunidadDevuelto(ComunidadApi comunidadApi) throws IOException{
        GradoDeConfianza gradoDeConfianza  = this.retrofit.create(GradoDeConfianza.class);
        Call<ComunidadApi> requestGradoConfianzaComunidad = gradoDeConfianza.comunidadApi(comunidadApi);
        Response<ComunidadApi> responseGradoConfianzaComunidad = requestGradoConfianzaComunidad.execute();
        return responseGradoConfianzaComunidad.body();
    }

    public UsuarioApi usuarioDevuelto(UsuarioApi usuarioApi) throws IOException{
        return new UsuarioApi();    //TODO HACER USUARIODEVUELTO
    }


}
