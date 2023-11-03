package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5;

import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GradoDeConfianza5Service {

    @POST("/gradoDeConfianza/usuario")
    Call<UsuarioDevuelto> usuarioApi(@Query("usuario") RequestUsuarioJSON usuario);

    @POST("/gradoDeConfianza/comunidad")
    Call<ComunidadDevuelta> comunidadApi(@Body RequestComunidadJSON comunidad);
}
