package com.project.creditservice.utilities.util;

import com.project.creditservice.models.dto.Client;
import com.project.creditservice.models.response.ClientClientServiceResponse;

public interface IClientUtils {
    Client clientClientServiceToClient(ClientClientServiceResponse client);
    ClientClientServiceResponse clientToClientClientServiceResponse(Client client);

}
