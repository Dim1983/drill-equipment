package com.loktionov.drillequipment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "equipment")
@XmlAccessorType(XmlAccessType.FIELD)
public class EquipmentDtoToXml {
    @XmlAttribute(name = "id")
    private Long equipmentId;
    @XmlAttribute(name = "name")
    private String equipmentName;
}
