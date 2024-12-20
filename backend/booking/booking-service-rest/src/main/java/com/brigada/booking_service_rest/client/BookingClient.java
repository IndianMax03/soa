package com.brigada.booking_service_rest.client;

import com.brigada.general.model.soap.SellRequest;
import com.brigada.general.model.soap.SellWithDiscountRequest;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class BookingClient extends WebServiceGatewaySupport {

    public SellRequest sell(SellRequest request) {
        SellRequest response = (SellRequest) getWebServiceTemplate().marshalSendAndReceive(request);
        return response;
    }

    public SellWithDiscountRequest sellWithDiscount(SellWithDiscountRequest request) {
        return (SellWithDiscountRequest) getWebServiceTemplate().marshalSendAndReceive(request);
    }

}
