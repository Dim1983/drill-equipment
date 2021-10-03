package com.loktionov.drillequipment.service.impl;

import com.loktionov.drillequipment.model.Equipment;
import com.loktionov.drillequipment.repository.EquipmentDao;
import com.loktionov.drillequipment.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    private EquipmentDao equipmentDao;

    @Autowired
    public EquipmentServiceImpl(EquipmentDao equipmentDao) {
        this.equipmentDao = equipmentDao;
    }

    @Override
    public void save(Equipment equipment) {
        equipmentDao.save(equipment);
    }

    @Override
    public Optional<Equipment> findById(Long id) {
        return equipmentDao.findById(id);
    }

    @Override
    public List<Equipment> findAll(Integer offset) {
        return equipmentDao.findAll(offset);
    }

    @Override
    public void update(Equipment equipment) {
        equipmentDao.update(equipment);
    }

    @Override
    public void deleteById(Long id) {
        equipmentDao.deleteById(id);
    }

    @Override
    public Map<String, Integer> getAllEquipmentOnWells(String wellNames) {
        return equipmentDao.getAllEquipmentOnWell(wellNames).get();
    }
}
