package com.brigada.general.model.soap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sellWithDiscountRequest", namespace = "http://www.brigada.com/general/model/soap")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellWithDiscountRequest {

    @XmlElement
    protected Long ticketId;

    @XmlElement
    protected Long personId;

    @XmlElement
    protected Integer discount;

    @XmlElement
    protected String result;

}
