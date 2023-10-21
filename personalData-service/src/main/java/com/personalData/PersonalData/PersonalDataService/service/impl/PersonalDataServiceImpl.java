package com.personalData.PersonalData.PersonalDataService.service.impl;

import com.personalData.PersonalData.PersonalDataService.entity.PersonalData;
import com.personalData.PersonalData.PersonalDataService.repository.IPersonalDataRepository;
import com.personalData.PersonalData.PersonalDataService.service.IPersonalDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PersonalDataServiceImpl implements IPersonalDataService {

    @Autowired
    IPersonalDataRepository personalDataRepository;

    @Override
    public List<PersonalData> findPersonalDataAll() {
        return personalDataRepository.findAll();
    }

    @Override
    public PersonalData getPersonalData(Long id) {
        return personalDataRepository.findById(id).orElse(null);
    }

    @Override
    public PersonalData createPersonalData(PersonalData personalData) {
        return personalDataRepository.save(personalData);
    }

    @Override
    public PersonalData updatePersonalData(PersonalData personalData) {
        PersonalData personalDataDB = getPersonalData(personalData.getId());
        if (personalDataDB == null) {
            return null;
        }
        return personalDataRepository.save(personalData);
    }

    @Override
    public PersonalData deletePersonalData(PersonalData personalData) {
        PersonalData personalDataDB = getPersonalData(personalData.getId());
        if (personalDataDB == null) {
            return null;
        }
        personalDataRepository.deleteById(personalData.getId());
        return personalDataDB;
    }

    @Override
    public PersonalData findByEmailAndPassword(String email, String password) {
        return personalDataRepository.findByEmailAndPassword(email, password);
    }
}
