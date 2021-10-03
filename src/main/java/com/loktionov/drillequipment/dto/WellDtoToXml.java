package com.loktionov.drillequipment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "well")
@XmlAccessorType(XmlAccessType.FIELD)
public class WellDtoToXml {
    @XmlAttribute(name = "id")
    private Long wellId;
    @XmlAttribute(name = "name")
    private String wellName;
    @XmlElement(name = "equipment")
    private List<EquipmentDtoToXml> equipments;
}
