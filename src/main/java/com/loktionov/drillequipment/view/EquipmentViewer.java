package com.loktionov.drillequipment.view;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EquipmentViewer {
    public void printEquipmentOmWell(Map<String, Integer> equipmentsOnWell) {
        for (Map.Entry<String, Integer> entryObj : equipmentsOnWell.entrySet()) {
            Map.Entry<String, Integer> entry = entryObj;
            System.out.printf("%s%s%d%s", entry.getKey(), " - ", entry.getValue(), "\n");
        }
    }
}
