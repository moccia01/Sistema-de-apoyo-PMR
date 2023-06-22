package domain.services.georef;

import domain.services.georef.entities.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioGeoref implements Localizador{
    private static ServicioGeoref instancia = null;
    private static int maximaCantidadRegistrosDefault = 200;
    private static final String urlApi = "https://apis.datos.gob.ar/georef/api/";
    private Retrofit retrofit;

    private ServicioGeoref() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioGeoref instancia(){
        if(instancia== null){
            instancia = new ServicioGeoref();
        }
        return instancia;
    }

    public ListadoDeProvincias listadoDeProvincias() throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeProvincias> requestProvinciasArgentinas = georefService.provincias();
        Response<ListadoDeProvincias> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
        return responseProvinciasArgentinas.body();
    }

    public Provincia provincia(String nombreProvincia) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeProvincias> requestProvincia = georefService.provincias(nombreProvincia);
        Response<ListadoDeProvincias> responseProvincia = requestProvincia.execute();
        ListadoDeProvincias provincias = responseProvincia.body();
        assert provincias != null;
        return provincias.provincias.get(0);
    }

    public ListadoDeMunicipios listadoDeMunicipiosDeProvincia(Provincia provincia) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeMunicipios> requestListadoDeMunicipios = georefService.municipios(provincia.id, "id, nombre", maximaCantidadRegistrosDefault);
        Response<ListadoDeMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
        return responseListadoDeMunicipios.body();
    }

    public Municipio municipio(String municipio) throws IOException{
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeMunicipios> requestMunicipios = georefService.municipios(municipio);
        Response<ListadoDeMunicipios> responseMunicipio = requestMunicipios.execute();
        ListadoDeMunicipios municipios = responseMunicipio.body();
        assert municipios != null;
        return municipios.municipios.get(0);
    }

    public Departamento departamento(String departamento) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeDepartamentos> requestDepartamentos = georefService.departamentos(departamento);
        Response<ListadoDeDepartamentos> responseDepartamento = requestDepartamentos.execute();
        ListadoDeDepartamentos departamentos = responseDepartamento.body();
        assert departamentos != null;
        return departamentos.departamentos.get(0);
    }
}