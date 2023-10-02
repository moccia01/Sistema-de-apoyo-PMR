package domain.json;


import domain.comunidades.Comunidad;
import domain.comunidades.Incidente;
import domain.comunidades.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PayloadDTO {
    private List<Usuario> usuarios;
    private List<Comunidad> comunidades;
    private List<Incidente> incidentes;



}
