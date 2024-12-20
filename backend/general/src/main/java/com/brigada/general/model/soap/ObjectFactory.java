package com.brigada.general.model.soap;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public SellRequest createSell() {
        return new SellRequest();
    }

    public SellWithDiscountRequest createSellWithDiscount() {
        return new SellWithDiscountRequest();
    }

}
