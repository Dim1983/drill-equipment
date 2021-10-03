package com.loktionov.drillequipment.repository.impl;

import com.loktionov.drillequipment.dto.EquipmentDtoToXml;
import com.loktionov.drillequipment.dto.WellDtoToXml;
import com.loktionov.drillequipment.model.Well;
import com.loktionov.drillequipment.repository.WellDao;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WellDaoImpl extends AbstractCrudDaoImpl<Well, Long> implements WellDao {
    private static final String SAVE_QUERY = "INSERT INTO well(name) values(:name)";
    private static final String DELETE_QUERY = "DELETE FROM well WHERE id = :id";
    private static final String UPDATE_QUERY = "UPDATE well SET name =:name WHERE id =:id";
    private static final String FIND_ALL_QUERY = "SELECT * FROM well limit :limit offset :offset";
    private static final String FIND_QUERY = "SELECT * FROM well WHERE id = :id";
    private static final RowMapper<Well> ROW_MAPPER = (resultSet, i) -> new Well(resultSet.getLong("id"),
            resultSet.getString("name"));
    private static final String FIND_BY_NAME = "SELECT * FROM well where name =:name";
    private static final String FIND_ALL_EQUIPMENTS_ON_WELL = "WITH wels AS(SELECT * FROM well group by well.id) SELECT wels.id as well_id, wels.name as well_name, equipment.id as equipment_id,equipment.name as equipment_name FROM wels INNER JOIN equipment WHERE wels.id = equipment.well_id order by wels.name;";

    public WellDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate, SAVE_QUERY, DELETE_QUERY, UPDATE_QUERY, FIND_ALL_QUERY, FIND_QUERY, ROW_MAPPER);
    }

    @Override
    public void insert(MapSqlParameterSource parameters, Well well) {
        parameters.addValue("id", well.getId());
        parameters.addValue("name", well.getName());
    }

    @Override
    public Optional<Well> findByName(String name) {
        SqlParameterSource param = new MapSqlParameterSource("name", name);

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_NAME, param, ROW_MAPPER));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Map<WellDtoToXml, List<EquipmentDtoToXml>> getAllWellsWithEquipment() {
        try {
            return jdbcTemplate.query(FIND_ALL_EQUIPMENTS_ON_WELL, new ResultSetExtractor<Map<WellDtoToXml, List<EquipmentDtoToXml>>>() {
                @Override
                public Map<WellDtoToXml, List<EquipmentDtoToXml>> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    Map<WellDtoToXml, List<EquipmentDtoToXml>> data = new LinkedHashMap<>();
                    while (resultSet.next()) {
                        WellDtoToXml wellDtoToXml = new WellDtoToXml(resultSet.getLong("well_id"), resultSet.getString("well_name"), new ArrayList<>());
                        data.putIfAbsent(wellDtoToXml, new ArrayList<>());
                        EquipmentDtoToXml equipment = new EquipmentDtoToXml(resultSet.getLong("equipment_id")
                                , resultSet.getString("equipment_name"));
                        data.get(wellDtoToXml).add(equipment);
                    }
                    return data;
                }
            });
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
