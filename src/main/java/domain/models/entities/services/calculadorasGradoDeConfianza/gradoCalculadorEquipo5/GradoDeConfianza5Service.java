package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5;

import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities.ComunidadApi5;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities.UsuarioApi5;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GradoDeConfianza5Service {

    @POST("usuario")
    Call<UsuarioApi5> usuarioApi(@Query("usuario") UsuarioApi5 usuario);

    @POST("comunidad")
    Call<ComunidadApi5> comunidadApi(@Query("comunidad") ComunidadApi5 comunidad);

}
