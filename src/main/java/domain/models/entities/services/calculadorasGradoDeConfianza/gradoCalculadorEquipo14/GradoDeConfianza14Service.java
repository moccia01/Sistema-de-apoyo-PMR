package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14;

import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities.ComunidadApi14;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities.UsuarioApi14;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities.ComunidadDevuelta;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities.UsuarioDevuelto;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GradoDeConfianza14Service {

    @POST("usuario")
    Call<UsuarioDevuelto> usuarioApi(@Query("usuario") UsuarioApi14 usuario);

    @POST("comunidad")
    Call<ComunidadDevuelta> comunidadApi(@Query("comunidad") ComunidadApi14 comunidad);
}
