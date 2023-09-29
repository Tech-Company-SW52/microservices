package com.fastporte.search_service.service;
import com.fastporte.search_service.entity.Transport;

import java.util.List;

public interface SearchService {

        public List<Transport> filerTransportByName(List<Transport> allTransport, String name);
}
