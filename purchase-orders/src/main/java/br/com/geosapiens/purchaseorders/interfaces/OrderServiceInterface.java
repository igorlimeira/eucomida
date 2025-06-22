package br.com.geosapiens.purchaseorders.interfaces;

import br.com.geosapiens.purchaseorders.dtos.ResponseOrderDTO;
import br.com.geosapiens.purchaseorders.dtos.SubmitOrderDTO;

public interface OrderServiceInterface {
    /**
     * Submits a new order.
     *
     * @param submitOrderDTO the order details to be submitted
     * @return a response containing the details of the submitted order
     */
    ResponseOrderDTO submitOrder(SubmitOrderDTO submitOrderDTO);

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId the ID of the order to retrieve
     * @return the details of the requested order
     */
    ResponseOrderDTO getOrderById(Long orderId);
}
