package domain.entidadesDeServicio;

import domain.db.EntidadPersistente;
import domain.rankings.CriterioRanking;
import domain.rankings.GeneradorDeRankings;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "organismo_de_control")
public class OrganismoDeControl extends EntidadPersistente {
    @Column
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="organismo_de_control_id")
    private List<Entidad> entidades;

    public OrganismoDeControl(String nombre) {
        this.nombre = nombre;
        this.entidades = new ArrayList<>();
    }

    public OrganismoDeControl() {
        this.entidades = new ArrayList<>();
    }

    public boolean estaAsociadoA(Entidad entidad) {
        return this.entidades.contains(entidad);
    }

}
