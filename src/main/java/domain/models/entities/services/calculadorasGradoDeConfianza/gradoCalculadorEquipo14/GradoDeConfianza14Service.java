package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14;

import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities.PayloadDTOApi14;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GradoDeConfianza14Service {

    @POST("/")
    Call<PayloadDTOApi14> usuarioComunidad(@Query("/") PayloadDTOApi14 json);
}
