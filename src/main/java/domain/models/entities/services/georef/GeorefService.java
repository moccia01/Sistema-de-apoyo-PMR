package domain.models.entities.services.georef;

import domain.models.entities.services.georef.entities.ListaDeDirecciones;
import domain.models.entities.services.georef.entities.ListadoDeDepartamentos;
import domain.models.entities.services.georef.entities.ListadoDeMunicipios;
import domain.models.entities.services.georef.entities.ListadoDeProvincias;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefService {
    @GET("provincias")
    Call<ListadoDeProvincias> provincias(@Query("max") int max);

    @GET("departamentos")
    Call<ListadoDeDepartamentos> departamentos(@Query("max") int max);

    @GET("municipios")
    Call<ListadoDeMunicipios> municipios(@Query("max") int max);

    @GET("provincias")
    Call<ListadoDeProvincias> provincias(@Query("nombre") String nombre);

    @GET("municipios")
    Call<ListadoDeMunicipios> municipios(@Query("nombre") String nombre);



    @GET("municipios")
    Call<ListadoDeMunicipios> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos);

    @GET("municipios")
    Call<ListadoDeMunicipios> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos, @Query("max") int max);

    @GET("departamentos")
    Call<ListadoDeDepartamentos> departamentos(@Query("departamento") String nombre);

    @GET("direcciones")
    Call<ListaDeDirecciones> direcciones(@Query("direccion") String direccion, @Query("departamento") String departamento);
}