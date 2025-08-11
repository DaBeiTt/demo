package so.wwb.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import so.wwb.api.util.CustomToolBox;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomToolTest {

    @AfterEach
    public void finishLog() {
        System.out.println("執行完畢");
    }

    @Test
    public void add() {
        assertEquals(3, CustomToolBox.add(1, 2));
    }
}
