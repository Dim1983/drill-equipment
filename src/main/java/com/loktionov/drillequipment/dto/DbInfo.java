package com.loktionov.drillequipment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@XmlRootElement(name = "dbinfo")
public class DbInfo {
    private Set<WellDtoToXml> well;
}
