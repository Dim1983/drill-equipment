package com.loktionov.drillequipment;

import com.loktionov.drillequipment.config.ApplicationConfig;
import com.loktionov.drillequipment.controller.MainController;
import com.loktionov.drillequipment.dataproviders.DataProvider;
import com.loktionov.drillequipment.dataproviders.DataReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        DataProvider dataProvider = context.getBean(DataProvider.class);
        dataProvider.createNewDatabase();
        dataProvider.createTable();

        MainController controller = context.getBean(MainController.class);
        DataReader dataReader = context.getBean(DataReader.class);

        boolean flag = true;

        while (flag) {
            switch (dataReader.getChoose().get()) {
                case 1:
                    controller.createEquipmentOnWell(dataReader.getWellName().get(),
                            dataReader.getEquipmentQuantity().get());
                    break;
                case 2:
                    controller.getAllEquipmentOnWell(dataReader.getWellName().get());
                    break;
                case 3:
                    controller.saveAllWellDataOnXml(dataReader.getGetFileName().get());
                    break;
                case 4:
                    flag = false;
            }
        }
    }
}
