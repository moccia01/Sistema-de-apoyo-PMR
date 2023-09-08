package domain.db;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public class EntidadPersistente {
    @Id
    @GeneratedValue
    private Long id;
}
