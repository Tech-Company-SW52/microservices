package com.fastporte.search_service.repository;

import com.fastporte.search_service.entity.Transport;

import java.util.List;

public interface SearchRepository {

    public List<Transport> filerTransportByName(List<Transport> allTransport, String name);

}
