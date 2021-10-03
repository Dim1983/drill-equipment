package com.loktionov.drillequipment.service;

import com.loktionov.drillequipment.model.Equipment;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EquipmentService {
    void save(Equipment equipment);

    Optional<Equipment> findById(Long id);

    List<Equipment> findAll(Integer offset);

    void update(Equipment equipment);

    void deleteById(Long id);

    Map<String, Integer> getAllEquipmentOnWells(String wellName);
}
