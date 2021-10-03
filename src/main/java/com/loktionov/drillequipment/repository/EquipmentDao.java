package com.loktionov.drillequipment.repository;

import com.loktionov.drillequipment.model.Equipment;

import java.util.Map;
import java.util.Optional;

public interface EquipmentDao extends CrudDao<Equipment, Long> {
    Optional<Map<String, Integer>> getAllEquipmentOnWell(String wellName);
}
