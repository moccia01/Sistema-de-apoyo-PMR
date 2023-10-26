package domain.models.entities.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class ServicioAPI {
    protected String urlApi;

    /*
    public ServicioAPI() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(this.obtenerUrlApi())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    */

    protected String obtenerUrlApi(){
        return urlApi;
    }

    public Retrofit cargarRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(this.obtenerUrlApi())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
