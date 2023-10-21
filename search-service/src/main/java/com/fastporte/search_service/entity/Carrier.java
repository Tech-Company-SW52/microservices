package com.fastporte.search_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Carrier {

        private Long id;

        private String firstName;

        private String lastName;

        private String username;

        private String photoUrl;

        private String email;

        private String password;

        private String phone;

        private String region;

        private LocalDate birthdate;

        private String description;

        public Carrier(long id, String firstName, String lastName, String email, String phone, String region, String username, LocalDate birthdate, String description) {
                this.id = id;
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.phone = phone;
                this.region = region;
                this.username = username;
                this.birthdate = birthdate;
                this.description = description;
        }
}
