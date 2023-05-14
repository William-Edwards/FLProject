package com.flooringmastery.dao;

import java.util.List;

import com.flooringmastery.dto.Order;

public interface OrderDao {

    /**
     * Adds the given order to the roster and associates it with the given
     * order id. If there is already a order associated with the given
     * order id it will return that order object, otherwise it will
     * return null.
     *
     * @param orderDate id with which order is to be associated
     * @param order     order to be added to the roster
     * @return the order object previously associated with the given
     *         order id if it exists, null otherwise
     */

    Order addOrder(String orderDate, Order order);

    /**
     * Returns the order object associated with the given orderDate.
     * Returns null if no such order exists
     *
     * @param orderDate order date
     * @param order     updated order
     * @return the edited Order object null if no such order exists
     */

    Order editOrder(String orderDate, Order order);

    /**
     * Returns the order object associated with the given orderDate.
     * Returns null if no such order exists
     *
     * @param orderDate of the Order to retrieve, format MMDDYYYY
     * @return the Order object list associated with the given orderDate,
     *         null if no such orderDate exists
     */

    List<Order> getAllOrder(String orderDate);

    /**
     * Returns from the roster the Order associated with the given params.
     * Returns the Order object or null if
     * there is no Order associated with the given params
     *
     * @param orderDate   format MMDDYYYY
     * @param orderNumber of Order to be returned
     * @return Order object or null if no Order
     *         was associated with the given Order date and number
     */

    Order getOrder(String orderDate, int orderNumber);

    /**
     * Removes from the roster the Order associated with the given params. null if
     * there is no Order associated with the given params
     *
     * @param orderDate   format MMDDYYYY
     * @param orderNumber of Order to be removed
     * @return Order object that was removed or null if no Order
     *         was associated with the given Order date and number
     */
    Order removeOrder(String orderDate, int orderNumber);
}
