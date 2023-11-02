package com.personalData.PersonalData.PersonalDataService.service;

/**
 * Esta interfaz define los métodos que deben implementarse para manejar los datos personales de los usuarios.
 * Los métodos están diseñados para ser genéricos y capaces de manejar diferentes tipos de usuarios
 * (como "carrier" y "client").
 * La implementación concreta de esta interfaz determinará la lógica específica para obtener y actualizar los
 * datos personales.
 */
public interface IPersonalDataService {
    Object getPersonalData(String userType, Long id);
    Object updatePersonalData(String userType, Long id, Object personalData);
}

