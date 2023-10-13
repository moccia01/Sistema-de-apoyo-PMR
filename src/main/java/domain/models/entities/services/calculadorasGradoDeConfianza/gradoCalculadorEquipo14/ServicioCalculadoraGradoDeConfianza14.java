package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.Incidente;
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

    //TODO Ver si hay que arreglar el nuestro dado que pasamos el PayloadDTO14 y recibimos el PayLoadDTO14 modificado
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
    public Usuario calcularGradoConfianzaParaUn(Usuario usuario, List<Incidente> incidentes) throws IOException {
        UsuarioApi14 usuarioApi14 = new UsuarioApi14();
        //usuarioApi14.cargar(usuario, incidentes);

        usuarioApi14 = usuarioDevuelto(usuarioApi14);

        return usuario;
    }

    @Override
    public Comunidad calcularGradoConfianzaParaUna(Comunidad comunidad, List<Incidente> incidentes) throws IOException  {
        return null;
    }

    //TODO CHEQUEAR SI EL CODIGO REPETIDO ES MERA COINCIDENCIA O SI HAY QUE SOLUCIONARLO
}
