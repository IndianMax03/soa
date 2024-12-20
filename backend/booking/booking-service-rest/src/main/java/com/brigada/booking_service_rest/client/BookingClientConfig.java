package com.brigada.booking_service_rest.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class BookingClientConfig {

    @Value("${booking-service.url}")
    private String bookingServiceUrl;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.brigada.general.model.soap");
        return marshaller;
    }

    @Bean
    public UrlConnectionMessageSender messageSender() {
        return new UrlConnectionMessageSender();
    }

    @Bean
    public BookingClient bookingClient(Jaxb2Marshaller marshaller, UrlConnectionMessageSender sender) {
        BookingClient client = new BookingClient();
        client.setDefaultUri(bookingServiceUrl);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        client.setMessageSender(sender);
        return client;
    }

}
