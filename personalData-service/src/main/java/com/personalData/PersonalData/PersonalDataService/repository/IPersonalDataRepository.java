package com.personalData.PersonalData.PersonalDataService.repository;

import com.personalData.PersonalData.PersonalDataService.entity.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonalDataRepository extends JpaRepository<PersonalData, Long> {
    PersonalData findByEmailAndPassword(String email, String password);
}
