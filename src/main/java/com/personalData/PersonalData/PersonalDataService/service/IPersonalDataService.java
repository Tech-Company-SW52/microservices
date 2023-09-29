package com.personalData.PersonalData.PersonalDataService.service;

import com.personalData.PersonalData.PersonalDataService.entity.PersonalData;
import java.util.List;

public interface IPersonalDataService {
    List<PersonalData> findAll();
    PersonalData get(Long id);
    PersonalData create(PersonalData personalData);
    PersonalData update(PersonalData personalData);
    void delete(PersonalData personalData);
    PersonalData findByEmailAndPassword(String email, String password);
}
