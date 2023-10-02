package domain.models.entities.builders;

import domain.models.entities.builders.exceptions.PrestacionDeServicioSinEntidadException;
import domain.models.entities.builders.exceptions.PrestacionDeServicioSinEstablecimientoException;
import domain.models.entities.builders.exceptions.PrestacionDeServicioSinServicioException;
import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.entities.entidadesDeServicio.Establecimiento;
import domain.models.entities.entidadesDeServicio.PrestacionDeServicio;
import domain.models.entities.entidadesDeServicio.Servicio;

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
