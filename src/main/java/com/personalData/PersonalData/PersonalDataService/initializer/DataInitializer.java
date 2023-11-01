package com.personalData.PersonalData.PersonalDataService.initializer;

import com.personalData.PersonalData.PersonalDataService.entity.PersonalData;
import com.personalData.PersonalData.PersonalDataService.repository.IPersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.time.LocalDate;
import java.util.List;

public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    public IPersonalDataRepository personalDataRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (personalDataRepository.count() == 0) {
            List<PersonalData> personalDataList = List.of(
                    // Datos de ejemplo
                    new PersonalData(null, "John", "Doe", "johndoe", "url", "john@example.com", "password", "1234567890", "North", LocalDate.of(1990, 1, 1), "Description", null, null),
                    new PersonalData(null, "Jane", "Doe", "janedoe", "url", "jane@example.com", "password", "0987654321", "South", LocalDate.of(1992, 2, 2), "Description", null, null)
            );

            personalDataRepository.saveAll(personalDataList);
        }
    }
}
