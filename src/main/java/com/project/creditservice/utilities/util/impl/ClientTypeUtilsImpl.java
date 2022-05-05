package com.project.creditservice.utilities.util.impl;

import com.project.creditservice.models.dto.ClientType;
import com.project.creditservice.models.response.ClientTypeClientServiceResponse;
import com.project.creditservice.utilities.util.IClientTypeUtils;
import org.springframework.stereotype.Component;

@Component
public class ClientTypeUtilsImpl implements IClientTypeUtils {
    @Override
    public ClientTypeClientServiceResponse clientTypeToClientTypeClientServiceResponse(ClientType clientType) {
        return ClientTypeClientServiceResponse.builder()
                .description(clientType.getDescription())
                .type(clientType.getType())
                .build();
    }

    @Override
    public ClientType clientTypeClientServiceResponseToClientType(ClientTypeClientServiceResponse clientType) {
        return ClientType.builder()
                .description(clientType.getDescription())
                .type(clientType.getType())
                .build();
    }
}
