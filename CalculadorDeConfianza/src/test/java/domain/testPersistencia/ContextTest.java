package domain.testPersistencia;

import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContextTest implements SimplePersistenceTest {

    @Test
    void contextUp() {              //Todo esto está corriendo en tpa-pmr-g145 en persistence.xml
        assertNotNull(entityManager());
    }

    @Test
    void contextUpWithTransaction() throws Exception {
        withTransaction(() -> {
        });
    }
}
