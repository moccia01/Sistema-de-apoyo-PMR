package domain.models.entities.services.calculadorasGradoDeConfianza.gradoCalculadorEquipo5.entities;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.Incidente;
import domain.models.entities.comunidad.Miembro;
import domain.models.entities.comunidad.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ComunidadApi5 {
    private String gradoConfianza;
    private List<UsuarioApi5> miembrosApi;

    public ComunidadApi5(){
        miembrosApi = new ArrayList<>();
    }
    public void cargar(Comunidad comunidad, List<Usuario> usuarios){
        this.setGradoConfianza(" ");
        usuarios.forEach(u->agregarAListaMiembro(u));
    }



    public void agregarAListaMiembro(Usuario usuario){
        UsuarioApi5 usuarioApi5 = new UsuarioApi5();
        usuarioApi5.setNombre(usuario.getNombre());
        usuarioApi5.setApellido(usuario.getApellido());
        usuarioApi5.setPuntosDeConfianza(usuario.getPuntosDeConfianza());
        usuarioApi5.setId(usuario.getId());
        miembrosApi.add(usuarioApi5);
    }




}
