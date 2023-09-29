package com.fastporte.search_service.service.impl;

import com.fastporte.search_service.entity.Transport;
import com.fastporte.search_service.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    @Override
    public List<Transport> filerTransportByName(List<Transport> allTransport, String name) {
        // Aplica el filtro para buscar servicios de transporte por nombre
        return allTransport.stream()
                .filter(transport -> transport.getType().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }


}
