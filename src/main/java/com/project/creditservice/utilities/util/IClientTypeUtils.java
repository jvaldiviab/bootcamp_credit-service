package com.project.creditservice.utilities.util;

import com.project.creditservice.models.dto.ClientType;
import com.project.creditservice.models.response.ClientTypeClientServiceResponse;

public interface IClientTypeUtils {
    ClientTypeClientServiceResponse clientTypeToClientTypeClientServiceResponse(ClientType clientType);
    ClientType clientTypeClientServiceResponseToClientType(ClientTypeClientServiceResponse clientType);
}
