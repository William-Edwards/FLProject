package com.flooringmastery;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import org.mockito.Mockito;

import com.flooringmastery.dao.OrderDao;
import com.flooringmastery.dao.ProductDao;
import com.flooringmastery.dao.TaxDao;
import com.flooringmastery.service.FlooringMasteryServiceLayerImpl;

/**
 * Unit test for simple App.
 */

public class FlooringMasteryServiceTest {

    OrderDao orderDao = Mockito.mock(OrderDao.class);
    ProductDao productDao = Mockito.mock(ProductDao.class);
    TaxDao taxDao = Mockito.mock(TaxDao.class);

    FlooringMasteryServiceLayerImpl service = new FlooringMasteryServiceLayerImpl(orderDao, productDao, taxDao);

    @Test
    public void testIsTodayOrFuture_withToday() {
        // Get today's date
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"));

        // Assert that the method returns true for today's date
        assertTrue(service.isTodayOrFuture(today));
    }

    @Test
    public void testIsTodayOrFuture_withFutureDate() {
        // Get a future date
        String futureDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("MMddyyyy"));

        // Assert that the method returns true for a future date
        assertTrue(service.isTodayOrFuture(futureDate));
    }

    @Test
    public void testIsTodayOrFuture_withPastDate() {
        // Get a past date
        String pastDate = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("MMddyyyy"));

        // Assert that the method returns false for a past date
        assertFalse(service.isTodayOrFuture(pastDate));
    }
}
