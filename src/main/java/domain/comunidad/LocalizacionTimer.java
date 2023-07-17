package domain.comunidad;

import java.util.List;
import java.util.TimerTask;

public class LocalizacionTimer extends TimerTask {
    private List<Comunidad> comunidades;
    private Miembro miembro;

    public  LocalizacionTimer(List<Comunidad> comunidades ,Miembro miembro){
        this.comunidades = comunidades;
        this.miembro = miembro;
    }

    @Override
    public void run(){
        mandarLocalizacion();
    }

    public void mandarLocalizacion(){
        comunidades.forEach(c -> c.recibirLocalizacion(this.miembro));
    }
}
