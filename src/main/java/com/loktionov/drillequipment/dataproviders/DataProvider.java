package com.loktionov.drillequipment.dataproviders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DataProvider {
    private final DataSource dataSource;

    @Autowired
    public DataProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createNewDatabase() {
        File file = new File("D:/javalearn/HRDZ/drill-equipment/test.db");
        if (file.delete()) {
            System.out.println("Old file is deleted!");
            try (Connection conn = dataSource.getConnection()) {
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createTable() {
        try (Connection conn = dataSource.getConnection()) {
            String dropWell = "drop table well";
            String dropEquipment = "drop table equipment";
            String sqlWell = "create table well(id INTEGER primary key,name varchar(32) not null);";
            String sqlEquipment = "create table equipment(id INTEGER primary key,name varchar(32) not null, well_id int not null );";
            Statement statement = conn.createStatement();

            statement.addBatch(dropWell);
            statement.addBatch(dropEquipment);
            statement.addBatch(sqlWell);
            statement.addBatch(sqlEquipment);
            statement.executeBatch();

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
