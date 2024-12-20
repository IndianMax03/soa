package com.brigada.booking_service_rest.service;

import com.brigada.booking_service_rest.client.BookingClient;
import com.brigada.general.model.soap.SellRequest;
import com.brigada.general.model.soap.SellWithDiscountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingClient client;

    public SellRequest sell(SellRequest request) {
        return client.sell(request);
    }

    public SellWithDiscountRequest sellWithDiscount(SellWithDiscountRequest request) {
        return client.sellWithDiscount(request);
    }

}
