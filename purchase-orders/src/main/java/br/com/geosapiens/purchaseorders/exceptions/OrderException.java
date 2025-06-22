package br.com.geosapiens.purchaseorders.exceptions;

import br.com.geosapiens.exceptions.models.BusinessException;
import br.com.geosapiens.purchaseorders.enums.EOrderError;


public class OrderException extends BusinessException {

    public OrderException(EOrderError error) {
        super(error.message(), error.code());
    }
}
