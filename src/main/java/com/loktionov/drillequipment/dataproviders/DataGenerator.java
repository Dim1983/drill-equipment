package com.loktionov.drillequipment.dataproviders;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataGenerator {

    public List<String> generateDataEquipment(Integer quantity, Long well_id) {
        List<String> equipmentsName = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J", "H", "I");

        return Stream.generate(() -> new StringBuffer()
                .append(equipmentsName.get(new Random().nextInt(10)))
                .append(" - ")
                .append(new Random().nextInt(10))).limit(quantity)
                .map(StringBuffer::toString)
                .collect(Collectors.toList());
    }
}
