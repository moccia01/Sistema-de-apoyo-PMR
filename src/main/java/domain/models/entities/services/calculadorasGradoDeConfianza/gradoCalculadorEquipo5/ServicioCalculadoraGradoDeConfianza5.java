package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5;

import domain.models.entities.comunidad.*;
import domain.models.entities.converters.GradoDeConfianzaConverter;
import domain.models.entities.converters.NombreGradoConfianzaAttributeConverter;
import domain.models.entities.mensajes.Configuraciones.MensajeEmail;
import domain.models.entities.mensajes.Configuraciones.MensajeWhatsApp;
import domain.models.entities.services.calculadorasGradoDeConfianza.CalculadorDeConfianzaAdapter;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServicioCalculadoraGradoDeConfianza5 implements CalculadorDeConfianzaAdapter {
    //"https://github.com/a-sandoval/servicio-entrega4-tpa-grupo5/tree/master/ServicioCalculadorGradoDeConfianza"
    private static ServicioCalculadoraGradoDeConfianza5 instancia = null;
    private final String urlApi = "https://raw.githubusercontent.com/gradoDeConfianza/"; //RARO, puede que no vaya
    private Retrofit retrofit;

    private ServicioCalculadoraGradoDeConfianza5() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioCalculadoraGradoDeConfianza5 instancia(){
        if(instancia== null){
            instancia = new ServicioCalculadoraGradoDeConfianza5();
        }
        return instancia;
    }

    public ComunidadDevuelta comunidadDevuelta(RequestComunidadJSON jsonComunidadIncidentes) throws IOException{
        GradoDeConfianza5Service gradoDeConfianza5Service = this.retrofit.create(GradoDeConfianza5Service.class);
        Call<ComunidadDevuelta> requestGradoConfianzaComunidad = gradoDeConfianza5Service.comunidadApi(jsonComunidadIncidentes);
        Response<ComunidadDevuelta> responseGradoConfianzaComunidad = requestGradoConfianzaComunidad.execute();
        return responseGradoConfianzaComunidad.body();
    }

    public UsuarioDevuelto usuarioDevuelto(RequestUsuarioJSON jsonUsuarioIncidentes) throws IOException{
        GradoDeConfianza5Service gradoDeConfianza5Service = this.retrofit.create((GradoDeConfianza5Service.class));
        Call<UsuarioDevuelto> requestGradoConfianzaUsuario = gradoDeConfianza5Service.usuarioApi(jsonUsuarioIncidentes);
        Response<UsuarioDevuelto> responseGradoConfianzaUsuario = requestGradoConfianzaUsuario.execute();
        return responseGradoConfianzaUsuario.body();
    }

    @Override
    public void calcularGradoConfianzaPara(List<Usuario> usuarios, List<Comunidad> comunidades, List<Incidente> incidentes) throws IOException {
       List<ComunidadDevuelta> comunidadDevueltas = new ArrayList<>();
       List<UsuarioDevuelto> usuariosDevueltos = new ArrayList<>();

       List<RequestComunidadJSON> requestComunidadJSONS = new ArrayList<>();
       List<RequestUsuarioJSON> requestUsuarioJSONS = new ArrayList<>();


       //transformo al tipo de dato solicitado de api
        usuarios.forEach(usuario -> {
            RequestUsuarioJSON usuarioJSON = new RequestUsuarioJSON();
            usuarioJSON.cargar(usuario, incidentes);
            requestUsuarioJSONS.add(usuarioJSON);
        });

        comunidades.forEach(comunidad -> {
            RequestComunidadJSON comunidadJSON = new RequestComunidadJSON();
            comunidadJSON.cargar(comunidad, incidentes);
            requestComunidadJSONS.add(comunidadJSON);
        });

        //ejecuto la operacion para que me retorne con los dato actualizado en la lista de devueltos
        requestComunidadJSONS.forEach(requestComunidad ->{
            try {
                comunidadDevueltas.add(this.comunidadDevuelta(requestComunidad));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        requestUsuarioJSONS.forEach(requestUsuarioJSON -> {
            try {
                usuariosDevueltos.add(this.usuarioDevuelto(requestUsuarioJSON));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //seteo a mi lista original
        usuariosDevueltos.forEach(usuarioDevuelto ->{
            usuarios.forEach(usuario -> this.actualizarUsuario(usuario, usuarioDevuelto));
        });

        comunidadDevueltas.forEach(comunidadDevuelta -> {
            comunidades.forEach(comunidad -> this.actualizarComunidad(comunidad, comunidadDevuelta));
        });
    }

    private void actualizarComunidad(Comunidad comunidad, ComunidadDevuelta comunidadDevuelta) {
        comunidad.setPuntosDeConfianza(comunidadDevuelta.getNuevoPuntaje());
        GradoDeConfianza gradoDeConfianza;
        gradoDeConfianza = GradoDeConfianzaConverter.crearGradoAPartirDeEnum(comunidadDevuelta.getGradoDeConfianzaActual());
        comunidad.setGradoDeConfianza(gradoDeConfianza);
    }

    private void actualizarUsuario(Usuario usuario, UsuarioDevuelto usuarioDevuelto) {
        usuario.setPuntosDeConfianza(usuarioDevuelto.getNuevoPuntaje());
        GradoDeConfianza gradoDeConfianza;
        gradoDeConfianza = GradoDeConfianzaConverter.crearGradoAPartirDeEnum(usuarioDevuelto.getGradoDeConfianzaActual());
        usuario.setGradoDeConfianza(gradoDeConfianza);
    }
}
