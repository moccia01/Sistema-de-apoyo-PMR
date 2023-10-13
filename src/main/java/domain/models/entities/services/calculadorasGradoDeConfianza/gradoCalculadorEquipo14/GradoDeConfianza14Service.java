package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14;

import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities.ComunidadApi14;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities.PayloadDTOApi14;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities.UsuarioApi14;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GradoDeConfianza14Service {

    @POST("usuario")
    Call<PayloadDTOApi14> usuarioComunidad(@Query("/") PayloadDTOApi14 json);

    /*
    @POST("comunidad")
    Call<ComunidadApi14> comunidadApi(@Query("comunidad") ComunidadApi14 comunidad);
    */
}
