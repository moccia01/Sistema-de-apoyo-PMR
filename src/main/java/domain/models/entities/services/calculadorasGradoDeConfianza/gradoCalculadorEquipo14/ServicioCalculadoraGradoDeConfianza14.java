package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.Usuario;
import domain.models.entities.services.calculadorasGradoDeConfianza.CalculadorDeConfianzaAdapter;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities.ComunidadApi14;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities.UsuarioApi14;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.GradoDeConfianza5Service;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities.ComunidadApi5;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities.UsuarioApi5;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioCalculadoraGradoDeConfianza14 implements CalculadorDeConfianzaAdapter {
    //"https://github.com/moccia01/servicio-entrega4-tpa-grupo14"
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

    public ComunidadApi14 comunidadDevuelto(ComunidadApi14 comunidadApi14) throws IOException{
        GradoDeConfianza14Service gradoDeConfianza14Service = this.retrofit.create(GradoDeConfianza14Service.class);
        Call<ComunidadApi14> requestGradoConfianzaComunidad = gradoDeConfianza14Service.comunidadApi(comunidadApi14);
        Response<ComunidadApi14> responseGradoConfianzaComunidad = requestGradoConfianzaComunidad.execute();
        return responseGradoConfianzaComunidad.body();
    }

    public UsuarioApi14 usuarioDevuelto(UsuarioApi14 usuarioApi14) throws IOException{
        GradoDeConfianza14Service gradoDeConfianza14Service = this.retrofit.create((GradoDeConfianza14Service.class));
        Call<UsuarioApi14> requestGradoConfianzaUsuario = gradoDeConfianza14Service.usuarioApi(usuarioApi14);
        Response<UsuarioApi14> responseGradoConfianzaUsuario = requestGradoConfianzaUsuario.execute();
        return responseGradoConfianzaUsuario.body();
    }

    @Override
    public Usuario calcularGradoConfianzaParaUn(Usuario usuario) {
        return null;
    }

    @Override
    public Comunidad calcularGradoConfianzaParaUna(Comunidad comunidad) {
        return null;
    }

    //TODO CHEQUEAR SI EL CODIGO REPETIDO ES MERA COINCIDENCIA O SI HAY QUE SOLUCIONARLO
}
