package com.project.creditservice.utilities.util;

import com.project.creditservice.models.dto.Client;
import com.project.creditservice.models.entity.CreditEntity;
import com.project.creditservice.models.request.CreditCreateRequest;
import com.project.creditservice.models.request.CreditUpdateRequest;
import com.project.creditservice.models.response.CreditFindBalancesResponse;

public interface ICreditUtils {
    CreditEntity creditCreateRequestToCredit(CreditCreateRequest creditCreateReq, Client client);
    CreditCreateRequest creditToCreditCreateRequest(CreditEntity credit);

    CreditEntity creditUpdateRequestToCredit(CreditUpdateRequest credit);
    CreditUpdateRequest creditToCreditUpdateCreateRequest(CreditEntity credit);

    CreditEntity creditFindBalancesResponseToCredit(CreditFindBalancesResponse credit);
    CreditFindBalancesResponse creditToCreditFindBalancesResponse(CreditEntity credit);
    CreditEntity fillCreditWithCreditUpdateRequest(CreditEntity credit, CreditUpdateRequest creditToUpdate);
}
