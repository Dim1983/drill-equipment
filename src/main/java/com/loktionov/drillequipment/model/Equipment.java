package com.loktionov.drillequipment.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@XmlRootElement(name = "equipment")
public class Equipment {
    @XmlAttribute
    private Long id;
    @XmlAttribute
    private String name;
    private Long well_id;
}
