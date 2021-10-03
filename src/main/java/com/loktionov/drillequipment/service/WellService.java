package com.loktionov.drillequipment.service;

import com.loktionov.drillequipment.dto.EquipmentDtoToXml;
import com.loktionov.drillequipment.model.Well;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WellService {
    void save(Well well);

    Optional<Well> findById(Long id);

    List<Well> findAll(Integer offset);

    void update(Well well);

    void deleteById(Long id);

    Optional<Well> getWellByName(String name);

    Map<String, List<EquipmentDtoToXml>> getWellWithEquipment(String fileName);
}
