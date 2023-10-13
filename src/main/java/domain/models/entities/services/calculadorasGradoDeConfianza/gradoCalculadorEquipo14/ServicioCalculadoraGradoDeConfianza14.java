package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.GradoDeConfianza;
import domain.models.entities.comunidad.Incidente;
import domain.models.entities.comunidad.Usuario;
import domain.models.entities.services.calculadorasGradoDeConfianza.CalculadorDeConfianzaAdapter;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities.ComunidadApi14;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities.GradoDeConfianzaApi14;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities.PayloadDTOApi14;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo14.entities.UsuarioApi14;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.GradoDeConfianza5Service;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities.ComunidadApi5;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities.ComunidadDevuelta;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities.UsuarioApi5;
import domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities.UsuarioDevuelto;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServicioCalculadoraGradoDeConfianza14 implements CalculadorDeConfianzaAdapter {

    private static ServicioCalculadoraGradoDeConfianza14 instancia = null;

    private final String urlApi = "aca iría la url si el servidor estuviera corriendo";

    private Retrofit retrofit;

    private ServicioCalculadoraGradoDeConfianza14() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioCalculadoraGradoDeConfianza14 instancia(){
        if(instancia== null){
            instancia = new ServicioCalculadoraGradoDeConfianza14();
        }
        return instancia;
    }

    public PayloadDTOApi14 jsonDevuelto(PayloadDTOApi14 jsonComunidadUsuario) throws IOException{
        GradoDeConfianza14Service gradoDeConfianza14Service = this.retrofit.create((GradoDeConfianza14Service.class));
        Call<PayloadDTOApi14> requestGradoConfianzaUsuario = gradoDeConfianza14Service.usuarioComunidad(jsonComunidadUsuario);
        Response<PayloadDTOApi14> responseGradoConfianzaUsuario = requestGradoConfianzaUsuario.execute();
        return responseGradoConfianzaUsuario.body();
    }



    public Comunidad calcularGradoConfianzaParaUna(Comunidad comunidad, List<Incidente> incidentes) throws IOException  {
        return null;
    }

    @Override
    public void calcularGradoConfianzaPara(List<Usuario> usuarios, List<Comunidad> comunidades, List<Incidente> incidentes) throws IOException {
        PayloadDTOApi14 json = new PayloadDTOApi14();

        json.cargar(usuarios, comunidades, incidentes);

        json = jsonDevuelto(json);

        List<UsuarioApi14> usuarioApi14s = new ArrayList<>();
        List<ComunidadApi14> comunidadApi14s = new ArrayList<>();

        usuarioApi14s.addAll(json.getUsuarios());
        comunidadApi14s.addAll(json.getComunidades());

        //TODO
        for(UsuarioApi14 usuarioApi14 : usuarioApi14s){
            usuarios.forEach(u->this.actualizarUsuario(u,usuarioApi14));
        }

        for(ComunidadApi14 comunidadApi14 :comunidadApi14s){
            comunidades.forEach(c->this.actualizarComunidad(c,comunidadApi14));
        }

/*
        usuario.setPuntosDeConfianza(json.getUsuarios().get(1).getPuntosDeConfianza());
        // usuario.setGradoDeConfianza(json.getUsuarios().get(1).getGradoDeConfianza());
        // TODO crear un gradoDeConfianza14Converter para transformar el int/enum que nos devuelven
        // a la clase GradoDeConfianza que queremos, seteando en la misma los puntosMinimos, maximos
        // y todo lo que requiera, haciendo un switch ya que por cada case, se crearia uno nuevo con
        // con el nombre y demas

        // TODO definir si la comunidad debería tener puntosDeConfianza y gradoDeConfianza
        // Si no requiere los puntos, definir que hacer con eso
        // Si no requiere el gradoDeConfianza, definir si lo ignoramos o no

        // comunidad.setPuntosDeConfianza(json.getComunidades().get(1).getPuntosDeConfianza());
        // comunidad.setGradoDeConfianza(json.getComunidades().get(1).getGradoDeConfianza());
*/
    }


    private void actualizarComunidad(Comunidad c, ComunidadApi14 comunidadDevuelta) {
    /*
        c.setPuntosDeConfianza(comunidadDevuelta.getNuevoPuntaje());
        //TODO implementar cambio de comunidad
        GradoDeConfianza gradoDeConfianza = new GradoDeConfianza();
        gradoDeConfianza = this.crearGradoDeConfianza(comunidadDevuelta.getGradoDeConfianzaActual());
        c.setGradoDeConfianza(gradoDeConfianza);

     */
    }

    private void actualizarUsuario(Usuario u, UsuarioApi14 usuarioApi14) {
        u.setPuntosDeConfianza(usuarioApi14.getPuntosDeConfianza());
        //TODO implementar cambio
        GradoDeConfianza gradoDeConfianza = new GradoDeConfianza();
        gradoDeConfianza = this.crearGradoDeConfianza(usuarioApi14.getGradoDeConfianza());
        u.setGradoDeConfianza(gradoDeConfianza);
    }

    //imprementar Para adaptar TODO
    public GradoDeConfianza crearGradoDeConfianza(GradoDeConfianzaApi14 gradoDeConfianzaActual){
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


}
