package domain.models.entities.services.gradoCalculadorEquipo5;

import domain.models.entities.services.gradoCalculadorEquipo5.entities.ComunidadApi;
import domain.models.entities.services.gradoCalculadorEquipo5.entities.UsuarioApi;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GradoDeConfianza {

    @POST("usuario")
    Call<UsuarioApi> usuarioApi(@Query("usuario") UsuarioApi usuario);

    @POST("comunidad")
    Call<ComunidadApi> comunidadApi(@Query("comunidad") ComunidadApi comunidad);

}
