package com.personalData.PersonalData.PersonalDataService.service.impl;

import com.personalData.PersonalData.PersonalDataService.entity.PersonalData;
import com.personalData.PersonalData.PersonalDataService.repository.IPersonalDataRepository;
import com.personalData.PersonalData.PersonalDataService.service.IPersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalDataServiceImpl implements IPersonalDataService {

    @Autowired
    private IPersonalDataRepository personalDataRepository;

    @Override
    public List<PersonalData> findAll() {
        return personalDataRepository.findAll();
    }

    @Override
    public PersonalData get(Long id) {
        Optional<PersonalData> dataOpt = personalDataRepository.findById(id);
        return dataOpt.orElse(null);
    }

    @Override
    public PersonalData create(PersonalData personalData) {
        return (PersonalData) personalDataRepository.save(personalData);
    }

    @Override
    public PersonalData update(PersonalData personalData) {
        if(personalData.getId() == null || !personalDataRepository.existsById(personalData.getId())) {
            return null; // o puedes lanzar una excepción
        }
        return (PersonalData) personalDataRepository.save(personalData);
    }

    @Override
    public void delete(PersonalData personalData) {
        personalDataRepository.delete(personalData);
    }

    @Override
    public PersonalData findByEmailAndPassword(String email, String password) {
        return personalDataRepository.findByEmailAndPassword(email, password);
    }
}
