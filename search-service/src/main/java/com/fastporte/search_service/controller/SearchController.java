package com.fastporte.search_service.controller;
import com.fastporte.search_service.entity.Transport;
import com.fastporte.search_service.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/search")
public class SearchController {

    @Autowired
    SearchService searchService;
   // @Autowired
   // private RestTemplate restTemplate;


    @GetMapping("/transport")
    public List<Transport> searchTransportByName(@RequestParam String name) {
        // Realiza una solicitud HTTP GET al endpoint de la otra API para obtener la lista de transportes
        //String otherApiUrl = "http://otra-api.com/api/transport?name=" + name;
        // List<Transport> transportList = restTemplate.getForObject(otherApiUrl, List.class);
        List<Transport> transportList = new ArrayList<>();
        transportList.add(new Transport(1L, "Transporte 1", "Descripción 1"));
        transportList.add(new Transport(2L, "Transporte 2", "Descripción 2"));
        transportList.add(new Transport(3L, "Transporte 3", "Descripción 3"));
        // Puedes aplicar cualquier lógica adicional que necesites en la lista de transportes obtenida
        // Por ejemplo, podrías filtrar o manipular la lista según tus necesidades
        List<Transport> filteredTransportList = searchService.filerTransportByName(transportList, name);

        return filteredTransportList;

    }


}
