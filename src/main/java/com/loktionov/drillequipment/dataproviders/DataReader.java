package com.loktionov.drillequipment.dataproviders;

import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.function.Supplier;

@Component
public class DataReader {
    private final Supplier<Integer> mainChoose = () -> {
        System.out.println("Choose: \n" +
                "1. Add equipment on well!\n" +
                "2. Get data ebout equipment on well!\n" +
                "3. Export data on XML\n" +
                "4. Exit\n");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();
    };

    private final Supplier<String> wellName = () -> {
        System.out.println("Enter well name");
        Scanner scanner = new Scanner(System.in);

        return scanner.next();
    };

    private final Supplier<Integer> equipmentQuantity = () -> {
        System.out.println("Enter equipment quantity");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();
    };

    private final Supplier<String> getWellName = () -> {
        System.out.println("Enter wells name use comma!");
        Scanner scanner = new Scanner(System.in);

        return scanner.next();
    };

    private final Supplier<String> getFileName = () -> {
        System.out.println("Enter name file for save!");
        Scanner scanner = new Scanner(System.in);

        return scanner.next();
    };

    public Supplier<Integer> getChoose() {
        return mainChoose;
    }

    public Supplier<Integer> getMainChoose() {
        return mainChoose;
    }

    public Supplier<String> getWellName() {
        return wellName;
    }

    public Supplier<Integer> getEquipmentQuantity() {
        return equipmentQuantity;
    }

    public Supplier<String> getGetWellName() {
        return getWellName;
    }

    public Supplier<String> getGetFileName() {
        return getFileName;
    }
}
