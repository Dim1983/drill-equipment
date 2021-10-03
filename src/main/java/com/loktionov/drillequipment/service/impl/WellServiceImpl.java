package com.loktionov.drillequipment.service.impl;

import com.loktionov.drillequipment.dto.DbInfo;
import com.loktionov.drillequipment.dto.EquipmentDtoToXml;
import com.loktionov.drillequipment.dto.WellDtoToXml;
import com.loktionov.drillequipment.model.Well;
import com.loktionov.drillequipment.repository.WellDao;
import com.loktionov.drillequipment.service.WellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WellServiceImpl implements WellService {
    private WellDao wellDao;

    @Autowired
    public WellServiceImpl(WellDao wellDao) {
        this.wellDao = wellDao;
    }

    @Override
    public void save(Well well) {
        wellDao.save(well);
    }

    @Override
    public Optional<Well> findById(Long id) {
        return wellDao.findById(id);
    }

    @Override
    public List<Well> findAll(Integer offset) {
        return wellDao.findAll(offset);
    }

    @Override
    public void update(Well well) {
        wellDao.update(well);
    }

    @Override
    public void deleteById(Long id) {
        wellDao.deleteById(id);
    }

    @Override
    public Optional<Well> getWellByName(String name) {
        return wellDao.findByName(name);
    }

    @Override
    public Map<String, List<EquipmentDtoToXml>> getWellWithEquipment(String fileName) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE-MMM-dd-yyyy--H-mm-aa", Locale.ENGLISH);
        String pathForSaveFile = "D:/javalearn/HRDZ/drill-equipment/xml/" + fileName + "-" + simpleDateFormat.format(new Date()) + ".xml";
        Set<WellDtoToXml> wellSet = wellDao.getAllWellsWithEquipment().entrySet()
                .stream().map(item -> new WellDtoToXml(item.getKey().getWellId(), item.getKey().getWellName(), item.getValue()))
                .collect(Collectors.toSet());

        DbInfo dbInfo = new DbInfo(wellSet);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DbInfo.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(dbInfo, new File(pathForSaveFile));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
