package com.project.creditservice.utilities.util;

import com.project.creditservice.models.dto.EnterpriseInfo;
import com.project.creditservice.models.dto.PersonInfo;
import com.project.creditservice.models.response.EnterpriseInfoClientServiceResponse;
import com.project.creditservice.models.response.PersonInfoClientServiceResponse;

public interface IClientInfoUtils {
    PersonInfo PersonInfoClientServiceResponseToPersonInfo(PersonInfoClientServiceResponse personInfo);
    PersonInfoClientServiceResponse  PersonInfoToPersonInfoClientServiceResponse(PersonInfo personInfo);

    EnterpriseInfo EnterpriseInfoClientServiceResponseToEnterpriseInfo(EnterpriseInfoClientServiceResponse enterpriseInfo);
    EnterpriseInfoClientServiceResponse EnterpriseInfoToEnterpriseInfoClientServiceResponse(EnterpriseInfo enterpriseInfo);
}
