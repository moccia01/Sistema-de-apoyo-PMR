package domain.comunidad;

import java.util.List;

public class Miembro {
    private Usuario usuario;
    private Rol rol;
    private List<Comunidad> comunidades;
    //private RolTemporal rolTemporal;  TODO ver que onda con el rol temporal del usuario, sino me equivoco, lo pusimos como ah, el miembro se quebró la pierna y está así por 3 meses
    private Config configuracion;

    public void recibirNotificacion(){

    }
}
