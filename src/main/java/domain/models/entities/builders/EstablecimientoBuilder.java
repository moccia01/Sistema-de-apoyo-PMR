package domain.models.entities.builders;

import domain.models.entities.builders.exceptions.EstablecimientoSinDireccionException;
import domain.models.entities.builders.exceptions.EstablecimientoSinServiciosException;
import domain.models.entities.entidadesDeServicio.Establecimiento;
import domain.models.entities.entidadesDeServicio.Servicio;

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
/*        if(this.establecimiento.getLocalizacion() == null){
            throw new EstablecimientoSinDireccionException();
        }
        if(this.establecimiento.getServicios() == null){
            throw new EstablecimientoSinServiciosException();
        }*/
        Establecimiento establecimientoARetornar = this.establecimiento;
        this.establecimiento = new Establecimiento();
        return establecimientoARetornar;
    }
}
