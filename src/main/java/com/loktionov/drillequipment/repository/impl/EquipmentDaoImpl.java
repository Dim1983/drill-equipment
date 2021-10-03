package com.loktionov.drillequipment.repository.impl;

import com.loktionov.drillequipment.model.Equipment;
import com.loktionov.drillequipment.repository.EquipmentDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class EquipmentDaoImpl extends AbstractCrudDaoImpl<Equipment, Long> implements EquipmentDao {
    private static final String SAVE_QUERY = "INSERT INTO equipment(name,well_id) values(:name, :well_id)";
    private static final String DELETE_QUERY = "DELETE FROM equipment WHERE id = :id";
    private static final String UPDATE_QUERY = "UPDATE equipment SET name =:name, well_id =:well_id WHERE id =:id";
    private static final String FIND_ALL_QUERY = "SELECT * FROM equipment limit :limit offset :offset";
    private static final String FIND_QUERY = "SELECT * FROM equipment WHERE id = :id";
    private static final RowMapper<Equipment> ROW_MAPPER = (resultSet, i) -> new Equipment(resultSet.getLong("id"),
            resultSet.getString("name"), resultSet.getLong("well_id"));

    private static final String FIND_ALL_EQUIPMENTS_ON_WELL = "WITH t AS(SELECT name,id FROM well WHERE name =:name)SELECT t.name, COUNT(e.id) as quantity FROM t INNER JOIN equipment e on e.well_id = t.id;";

    public EquipmentDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate, SAVE_QUERY, DELETE_QUERY, UPDATE_QUERY, FIND_ALL_QUERY, FIND_QUERY, ROW_MAPPER);
    }

    @Override
    public void insert(MapSqlParameterSource parameters, Equipment equipment) {
        parameters.addValue("id", equipment.getId());
        parameters.addValue("name", equipment.getName());
        parameters.addValue("well_id", equipment.getWell_id());
    }

    @Override
    public Optional<Map<String, Integer>> getAllEquipmentOnWell(String wellName) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", wellName);

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_ALL_EQUIPMENTS_ON_WELL, params,
                    (resultSet, i) -> new HashMap<>(
                            Map.of(resultSet.getString("name"), resultSet.getInt("quantity"))
                    )));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
