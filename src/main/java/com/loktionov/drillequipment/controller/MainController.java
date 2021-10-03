package com.loktionov.drillequipment.controller;

import com.loktionov.drillequipment.dataproviders.DataGenerator;
import com.loktionov.drillequipment.model.Equipment;
import com.loktionov.drillequipment.model.Well;
import com.loktionov.drillequipment.service.EquipmentService;
import com.loktionov.drillequipment.service.WellService;
import com.loktionov.drillequipment.view.EquipmentViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {
    private final WellService wellService;
    private final EquipmentService equipmentService;
    private final DataGenerator dataGenerator;
    private final EquipmentViewer equipmentViewer;

    @Autowired
    public MainController(WellService wellService, EquipmentService equipmentService, DataGenerator dataGenerator,
                          EquipmentViewer equipmentViewer) {
        this.wellService = wellService;
        this.equipmentService = equipmentService;
        this.dataGenerator = dataGenerator;
        this.equipmentViewer = equipmentViewer;
    }

    public void createEquipmentOnWell(String wellName, Integer quantity) {
        if (wellService.getWellByName(wellName).isEmpty()) {
            wellService.save(new Well(null, wellName));
        }

        List<String> equipmentsName = dataGenerator.generateDataEquipment(quantity, wellService.getWellByName(wellName)
                .get().getId());

        for (String name : equipmentsName) {
            Equipment equipment = new Equipment(null, name, wellService.getWellByName(wellName).get().getId());
            equipmentService.save(equipment);
        }
    }

    public void getAllEquipmentOnWell(String wellName) {
        Arrays.stream(wellName.split(","))
                .map(name -> equipmentService.getAllEquipmentOnWells(name))
                .forEach(equipmentViewer::printEquipmentOmWell);
    }

    public void saveAllWellDataOnXml(String fileName) {
        wellService.getWellWithEquipment(fileName);
    }
}
