package domain.testEntrega5;

import domain.server.init.Initializer;
import org.junit.jupiter.api.Test;

public class InitTest {

    @Test
    public void initTest(){
        Initializer initializer = new Initializer();
        initializer.init();
    }
}
