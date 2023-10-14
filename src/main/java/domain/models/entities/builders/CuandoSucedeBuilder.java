package domain.models.entities.builders;

import domain.models.entities.mensajes.Configuraciones.CuandoSucede;

public class CuandoSucedeBuilder {
    public CuandoSucede cuandoSucede;

    public CuandoSucedeBuilder(){
        this.cuandoSucede = new CuandoSucede();
    }

    public CuandoSucede construir(){
        return cuandoSucede;
    }

}
