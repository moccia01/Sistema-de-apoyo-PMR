package domain.builders;

import domain.builders.exceptions.PrestacionDeServicioSinEntidadException;
import domain.builders.exceptions.PrestacionDeServicioSinEstablecimientoException;
import domain.builders.exceptions.PrestacionDeServicioSinServicioException;
import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.Establecimiento;
import domain.entidadesDeServicio.PrestacionDeServicio;
import domain.entidadesDeServicio.Servicio;

public class PrestacionDeServicioBuilder {
    private PrestacionDeServicio prestacionDeServicio;

    public PrestacionDeServicioBuilder(){
        this.prestacionDeServicio = new PrestacionDeServicio();
    }

    public PrestacionDeServicioBuilder conEntidad(Entidad entidad){
        this.prestacionDeServicio.setEntidad(entidad);
        return this;
    }

    public PrestacionDeServicioBuilder conEstablecimiento(Establecimiento establecimiento){
        this.prestacionDeServicio.setEstablecimiento(establecimiento);
        return this;
    }

    public PrestacionDeServicioBuilder conServicio(Servicio servicio){
        this.prestacionDeServicio.setServicio(servicio);
        return this;
    }

    public PrestacionDeServicio construir(){
        if(this.prestacionDeServicio.getEntidad() == null){
            throw new PrestacionDeServicioSinEntidadException();
        }
        if(this.prestacionDeServicio.getEstablecimiento() == null){
            throw new PrestacionDeServicioSinEstablecimientoException();
        }
        if(this.prestacionDeServicio.getServicio() == null){
            throw new PrestacionDeServicioSinServicioException();
        }

        return prestacionDeServicio;
    }
}
