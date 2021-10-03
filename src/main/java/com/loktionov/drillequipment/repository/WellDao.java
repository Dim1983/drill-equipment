package com.loktionov.drillequipment.repository;

import com.loktionov.drillequipment.dto.EquipmentDtoToXml;
import com.loktionov.drillequipment.dto.WellDtoToXml;
import com.loktionov.drillequipment.model.Well;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WellDao extends CrudDao<Well, Long> {
    Optional<Well> findByName(String name);

    Map<WellDtoToXml, List<EquipmentDtoToXml>> getAllWellsWithEquipment();
}
