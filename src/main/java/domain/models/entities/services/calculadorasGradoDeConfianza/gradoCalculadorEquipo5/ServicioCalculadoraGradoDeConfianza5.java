package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5;

import domain.models.entities.comunidad.*;
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


    public Usuario calcularGradoConfianzaParaUn(Usuario usuario, List<Incidente> incidentes) throws IOException {
        UsuarioDevuelto usuarioDevuelto;
        RequestUsuarioJSON usuarioJSON = new RequestUsuarioJSON();
        usuarioJSON.cargar(usuario, incidentes);
        GradoDeConfianza gradoDeConfianza = new GradoDeConfianza();

        usuarioDevuelto = this.usuarioDevuelto(usuarioJSON);

        this.crearGradoDeConfianza(gradoDeConfianza,usuarioDevuelto.gradoDeConfianzaActual);

        usuario.setPuntosDeConfianza(usuarioDevuelto.nuevoPuntaje);
        //usuario.setGradoDeConfianza(gradoDeConfianza);
        NombreGradoConfianzaAttributeConverter converterIntToEnum = new NombreGradoConfianzaAttributeConverter();
        usuario.getGradoDeConfianza().setNombreGradoConfianza(converterIntToEnum.convertIntToEntityAttribute(usuarioDevuelto.gradoDeConfianzaActual));

        //TODO VER COMO CREAR UN NUEVO GRADO DE CONFIANZA PARA EL USUARIO PORQUE SINO ESTA TODO MAL
        // ESTAMOS CAMBIANDOLE EL NOMBRE ATADO CON ALAMBRE, EN VEZ DE CREAR UNO NUEVO CON TODOS LOS ATRIBUTOS CORRECTOS
        return usuario;
    }

    public void crearGradoDeConfianza(GradoDeConfianza gradoDeConfianza, Integer gradoDeConfianzaActual){
        /*
        return switch (gradoDeConfianza) {
            case 0 ->onfianza.NO_CONFIABLE);
            case 1 -> new MensajeEmail();
            default -> null;
        };
         */
    }

    public Comunidad calcularGradoConfianzaParaUna(Comunidad comunidad, List<Incidente> incidentes) throws IOException {
        ComunidadDevuelta comunidadDevuelta = new ComunidadDevuelta();
        RequestComunidadJSON comunidadJSON = new RequestComunidadJSON();

        comunidadJSON.cargar(comunidad,incidentes);

        //TODO devuelta lo mismo, definir si la comunidad tiene que tener puntos/grado de confianza
        comunidadDevuelta = this.comunidadDevuelta(comunidadJSON);
        //NombreGradoConfianzaAttributeConverter converterIntToEnum = new NombreGradoConfianzaAttributeConverter();
        //comunidad.setGradoConfianza(converterIntToEnum.convertIntToEntityAttribute(comunidadDevuelta.getGradoDeConfianzaActual());

        return comunidad;
    }

    @Override
    public void calcularGradoConfianzaPara(Usuario usuario, Comunidad comunidad, List<Incidente> incidentes) throws IOException {
        usuario = this.calcularGradoConfianzaParaUn(usuario, incidentes);
        comunidad = this.calcularGradoConfianzaParaUna(comunidad, incidentes);
    }
}
