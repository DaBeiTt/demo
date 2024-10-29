package so.wwb.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomToolTest {

    private CustomToolBox ctb;

    @BeforeEach
    public void setUp() {
        ctb = new CustomToolBox();
    }

    @AfterEach
    public void finishLog() {
        System.out.println("執行完畢");
    }

    @Test
    public void add() {
        assertEquals(3, ctb.add(1,2));
    }
}
