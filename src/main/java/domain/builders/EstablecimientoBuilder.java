package domain.builders;

import domain.builders.exceptions.EstablecimientoSinDireccionException;
import domain.builders.exceptions.EstablecimientoSinServiciosException;
import domain.entidadesDeServicio.Establecimiento;
import domain.entidadesDeServicio.Servicio;
import domain.services.georef.entities.Provincia;

public class EstablecimientoBuilder {
    private Establecimiento establecimiento;

    public EstablecimientoBuilder() {
        this.establecimiento = new Establecimiento();
    }

    public EstablecimientoBuilder conNombre(String nombre){
        this.establecimiento.setNombre(nombre);
        return this;
    }

    public EstablecimientoBuilder conLocalizacion(String provincia, String departamento, String direccion){
        this.establecimiento.setLocalizacion(provincia, departamento, direccion);
        return this;
    }

    public EstablecimientoBuilder conServicios(Servicio ... servicios) {
        this.establecimiento.agregarServicios(servicios);
        return this;
    }

    public Establecimiento construir(){
        if(this.establecimiento.getLocalizacion() == null){
            throw new EstablecimientoSinDireccionException();
        }
        if(this.establecimiento.getServicios() == null){
            throw new EstablecimientoSinServiciosException();
        }

        return establecimiento;
    }
}
