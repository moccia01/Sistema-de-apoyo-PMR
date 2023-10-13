package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5;

import domain.models.entities.comunidad.*;
import domain.models.entities.mensajes.Configuraciones.MensajeEmail;
import domain.models.entities.mensajes.Configuraciones.MensajeWhatsApp;
import domain.models.entities.services.calculadorasGradoDeConfianza.CalculadorDeConfianzaAdapter;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
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


    //@Override
    public Usuario calcularGradoConfianzaParaUn(Usuario usuario, List<Incidente> incidentes) throws IOException{
        UsuarioDevuelto usuarioDevuelto = new UsuarioDevuelto();
        RequestUsuarioJSON usuarioJSON = new RequestUsuarioJSON();
        usuarioJSON.cargar(usuario, incidentes);
        GradoDeConfianza gradoDeConfianza = new GradoDeConfianza();

        usuarioDevuelto = this.usuarioDevuelto(usuarioJSON);

        this.crearGradoDeConfianza(gradoDeConfianza,usuarioDevuelto.gradoDeConfianzaActual);

        usuario.setPuntosDeConfianza(usuarioDevuelto.nuevoPuntaje);
        //usuario.setGradoDeConfianza(gradoDeConfianza);

        return usuario;
    }

    public void crearGradoDeConfianza(GradoDeConfianza gradoDeConfianza, Integer gradoDeConfianzaActual){
        return switch (gradoDeConfianza) {
            case 0 -> gradoDeConfianza.setNombreGradoConfianza(NombreGradoConfianza.NO_CONFIABLE);
            case 1 -> new MensajeEmail();
            default -> null;
        };
    }

    @Override
    public Comunidad calcularGradoConfianzaParaUna(Comunidad comunidad, List<Incidente> incidentes) throws IOException {
        ComunidadDevuelta comunidadDevuelta = new ComunidadDevuelta();
        RequestComunidadJSON comunidadJSON = new RequestComunidadJSON();

        comunidadJSON.cargar(comunidad,incidentes);

        comunidadDevuelta = this.comunidadDevuelta(comunidadJSON);

        comunidad.setGradoConfianza(comunidadDevuelta.getGradoDeConfianzaActual());

        return comunidad;
    }
}
