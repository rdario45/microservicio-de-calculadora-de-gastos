package domain;

import factory.EventFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EventTest {

    private Gasto gasto;

    @Before
    public void forEach(){
        gasto = new EventFactory().get();
    }

    @Test
    public void testDates() {
        assertTrue(gasto.getStartDate().isBefore(gasto.getFinishDate()));
    }
}