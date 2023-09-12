package domain.repositorios;

import domain.comunidad.Comunidad;
import domain.comunidad.Incidente;
import domain.comunidad.Miembro;
import domain.comunidad.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RepositorioComunidades implements WithSimplePersistenceUnit {
    private List<Comunidad> comunidades;

    public RepositorioComunidades() {
        this.comunidades = new ArrayList<>();
    }

    public void agregarComunidades(Comunidad ... comunidades){
        Collections.addAll(this.comunidades,comunidades);
    }

    // EntityManager.beginTransaction();
    //query de las comunidades
    //comunidades = EntityManager.commit();
    public List<Comunidad> obtenerComunidades(){
        if(this.comunidades == null){
            comunidades = entityManager()
                    .createQuery("from Comunidad")
                    .getResultList();
        }
        return this.comunidades;
    }
}
