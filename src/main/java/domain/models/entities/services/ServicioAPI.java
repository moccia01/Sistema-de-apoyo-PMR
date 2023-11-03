package domain.models.entities.services;

import lombok.Getter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
@Getter
public abstract class ServicioAPI {
    protected Retrofit retrofit;

    public  ServicioAPI(String urlApi){
        this.retrofit=new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
