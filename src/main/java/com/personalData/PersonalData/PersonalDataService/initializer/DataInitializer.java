package com.personalData.PersonalData.PersonalDataService.initializer;

import com.personalData.PersonalData.PersonalDataService.entity.PersonalData;
import com.personalData.PersonalData.PersonalDataService.repository.IPersonalDataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final IPersonalDataRepository personalDataRepository;

    public DataInitializer(IPersonalDataRepository personalDataRepository) {
        this.personalDataRepository = personalDataRepository;
    }

    @Override
    public void run(String... args) {
        if (personalDataRepository.count() == 0) {
            List<PersonalData> personalDataList = List.of(
                    PersonalData.builder()
                            .firstName("John")
                            .lastName("Doe")
                            .username("johndoe")
                            .photoUrl("john-doe.jpg")
                            .email("john@doe.com")
                            .password("123456")
                            .phone("123456789")
                            .region("Lima")
                            .birthdate(LocalDate.of(1990, 1, 1))
                            .description("I'm John Doe")
                            .build(),
                    PersonalData.builder()
                            .firstName("Jane")
                            .lastName("Doe")
                            .username("janedoe")
                            .photoUrl("jane-doe.jpg")
                            .email("jane@doe.com")
                            .password("123456")
                            .phone("123456789")
                            .region("Lima")
                            .birthdate(LocalDate.of(1990, 1, 1))
                            .description("I'm Jane Doe")
                            .build()
            );

            personalDataRepository.saveAll(personalDataList);
        }
    }
}
