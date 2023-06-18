package domain.cargaDeDatos;

import domain.EntidadPrestadora;
import lombok.Getter;

@Getter
public class CargaEntidadesPrestadoras extends CargaDatosTemplate <EntidadPrestadora>{

    public CargaEntidadesPrestadoras(String token) {
        super(token);
    }

    @Override
    public EntidadPrestadora transformarLinea(String[] campos) {
        String nombre = campos[0];
        return new EntidadPrestadora(nombre);
    }
}