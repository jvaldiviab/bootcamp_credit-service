package com.project.creditservice.utilities.util.impl;

import com.project.creditservice.models.dto.Client;
import com.project.creditservice.models.response.ClientClientServiceResponse;
import com.project.creditservice.utilities.util.IClientInfoUtils;
import com.project.creditservice.utilities.util.IClientTypeUtils;
import com.project.creditservice.utilities.util.IClientUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientUtilsImpl implements IClientUtils {

    private final IClientTypeUtils clientTypeUtils;
    private final IClientInfoUtils clientInfoUtils;

    @Override
    public Client clientClientServiceToClient(ClientClientServiceResponse client) {
        return Client.builder()
                .id(client.getId())
                .clientType(clientTypeUtils.clientTypeClientServiceResponseToClientType(client.getClientType()))
                .status(client.getStatus())
                .personInfo(clientInfoUtils.PersonInfoClientServiceResponseToPersonInfo(client.getPersonInfo()))
                //.enterpriseInfo(clientInfoUtils.EnterpriseInfoClientServiceResponseToEnterpriseInfo(client.getEnterpriseInfo()))
                .build();
    }

    @Override
    public ClientClientServiceResponse clientToClientClientServiceResponse(Client client) {
        return ClientClientServiceResponse.builder()
                .id(client.getId())
                .clientType(clientTypeUtils.clientTypeToClientTypeClientServiceResponse(client.getClientType()))
                .status(client.getStatus())
                .personInfo(clientInfoUtils.PersonInfoToPersonInfoClientServiceResponse(client.getPersonInfo()))
                .enterpriseInfo(clientInfoUtils.EnterpriseInfoToEnterpriseInfoClientServiceResponse(client.getEnterpriseInfo()))
                .build();
    }
}