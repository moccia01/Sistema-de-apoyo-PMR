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

/*
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
        */

    public GradoDeConfianza crearGradoDeConfianza(Integer gradoDeConfianzaActual){
        GradoDeConfianza gradoDeConfianza1 = new GradoDeConfianza();
        /*
        return switch (gradoDeConfianza) {
            case 0 ->onfianza.NO_CONFIABLE);
            case 1 -> new MensajeEmail();
            default -> null;
        };
         */
        return gradoDeConfianza1;
    }

    public Comunidad calcularGradoConfianzaParaUna(Comunidad comunidad, List<Incidente> incidentes) throws IOException {
        ComunidadDevuelta comunidadDevuelta = new ComunidadDevuelta();
        RequestComunidadJSON comunidadJSON = new RequestComunidadJSON();

        comunidadJSON.cargar(comunidad,incidentes);
        //traer de base de datos
        //TODO devuelta lo mismo, definir si la comunidad tiene que tener puntos/grado de confianza
        comunidadDevuelta = this.comunidadDevuelta(comunidadJSON);
        //NombreGradoConfianzaAttributeConverter converterIntToEnum = new NombreGradoConfianzaAttributeConverter();
        //comunidad.setGradoConfianza(converterIntToEnum.convertIntToEntityAttribute(comunidadDevuelta.getGradoDeConfianzaActual());

        return comunidad;
    }

    @Override
    public void calcularGradoConfianzaPara(List<Usuario> usuarios, List<Comunidad> comunidades, List<Incidente> incidentes) throws IOException {
       List<ComunidadDevuelta> comunidadDevueltas = new ArrayList<>();
       List<UsuarioDevuelto> usuarioDevueltos = new ArrayList<>();

       List<RequestComunidadJSON> requestComunidadJSONS = new ArrayList<>();
       List<RequestUsuarioJSON> requestUsuarioJSONS = new ArrayList<>();

       RequestComunidadJSON comunidadJSON = new RequestComunidadJSON();
       RequestUsuarioJSON usuarioJSON = new RequestUsuarioJSON();

       //transformo al tipo de dato solicitado de api
        for(Usuario usuario : usuarios){
            usuarioJSON.cargar(usuario,incidentes);
            requestUsuarioJSONS.add(usuarioJSON);
        }

        for(Comunidad comunidad :comunidades){
            comunidadJSON.cargar(comunidad,incidentes);
            requestComunidadJSONS.add(comunidadJSON);
        }


        //ejecuto la operacion para que me retorne con los dato actualizado en la lista de devueltos
        for(RequestComunidadJSON comunidadJSON1 : requestComunidadJSONS){
            comunidadDevueltas.add(this.comunidadDevuelta(comunidadJSON1));
        }

        for(RequestUsuarioJSON usuarioJSON1 :requestUsuarioJSONS ){
            usuarioDevueltos.add(this.usuarioDevuelto(usuarioJSON1));
        }

        //seteo a mi lista original
        for(UsuarioDevuelto usuarioDevuelto : usuarioDevueltos){
            usuarios.forEach(u->this.actualizarUsuario(u,usuarioDevuelto));
        }

        for(ComunidadDevuelta comunidadDevuelta :comunidadDevueltas){
            comunidades.forEach(c->this.actualizarComunidad(c,comunidadDevuelta));
        }

    }

    private void actualizarComunidad(Comunidad c, ComunidadDevuelta comunidadDevuelta) {
    /*
        c.setPuntosDeConfianza(comunidadDevuelta.getNuevoPuntaje());
        //TODO implementar cambio de comunidad
        GradoDeConfianza gradoDeConfianza = new GradoDeConfianza();
        gradoDeConfianza = this.crearGradoDeConfianza(comunidadDevuelta.getGradoDeConfianzaActual());
        c.setGradoDeConfianza(gradoDeConfianza);

     */
    }

    private void actualizarUsuario(Usuario u, UsuarioDevuelto usuarioDevuelto) {
        u.setPuntosDeConfianza(usuarioDevuelto.getNuevoPuntaje());
        //TODO implementar cambio
        GradoDeConfianza gradoDeConfianza = new GradoDeConfianza();
        gradoDeConfianza = this.crearGradoDeConfianza(usuarioDevuelto.getGradoDeConfianzaActual());
        u.setGradoDeConfianza(gradoDeConfianza);
    }



}
