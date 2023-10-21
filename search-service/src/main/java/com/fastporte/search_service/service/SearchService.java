package com.fastporte.search_service.service;
import com.fastporte.search_service.entity.Vehicle;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public interface SearchService {

        public List<Vehicle> filterTransportByVehicleType(List<Vehicle> allVehicle, String type);

        public List<Vehicle> filterTransportByQuantity(List<Vehicle> allVehicle, Long quantity);


        public List<Vehicle> filterTransportByCarrierRegion(List<Vehicle> allVehicle, String region) ;

        public List<Vehicle> filterTransportByCarrierDescription(List<Vehicle> allVehicle, String description) ;

        public List<Vehicle> filterTransportByCarrierPhone(List<Vehicle> allVehicle, String phone) ;

        public List<Vehicle> filterTransportByCarrierUsername(List<Vehicle> allVehicle, String username) ;

        public List<Vehicle> filterTransportByCarrierEmail(List<Vehicle> allVehicle, String email) ;


        public List<Vehicle> filterTransportByCarrierFullNameLike(List<Vehicle> allVehicle, String fullname) ;


}
