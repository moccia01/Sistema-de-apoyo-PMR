package domain.cargaDeDatos;

import domain.OrganismoDeControl;
import lombok.Getter;

@Getter
public class CargaOrganismosControl extends CargaDatosTemplate <OrganismoDeControl>{

    public CargaOrganismosControl(String token) {
        super(token);
    }

    @Override
    protected OrganismoDeControl transformarLinea(String[] campos) {
        String nombre = campos[0];
        return new OrganismoDeControl(nombre);
    }
}