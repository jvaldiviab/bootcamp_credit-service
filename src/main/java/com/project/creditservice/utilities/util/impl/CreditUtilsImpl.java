package com.project.creditservice.utilities.util.impl;

import com.project.creditservice.models.dto.Client;
import com.project.creditservice.models.dto.CreditSpecifications;
import com.project.creditservice.models.entity.CreditEntity;
import com.project.creditservice.models.request.CreditCreateRequest;
import com.project.creditservice.models.request.CreditUpdateRequest;
import com.project.creditservice.models.response.CreditFindBalancesResponse;
import com.project.creditservice.utilities.constants.Constants;
import com.project.creditservice.utilities.util.ICreditUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreditUtilsImpl implements ICreditUtils {
    private final Constants constants;

    @Override
    public CreditEntity creditCreateRequestToCredit(CreditCreateRequest creditCreateReq, Client client) {
        return CreditEntity.builder()
                .client(client)
                .membershipDate(new Date(System.currentTimeMillis()))
                .creditSpecifications(CreditSpecifications.builder()
                        .closingDay(creditCreateReq.getCreditSpecifications().getClosingDay())
                        .numberInstallments(creditCreateReq.getCreditSpecifications().getNumberInstallments())
                        .interestPercentage(creditCreateReq.getCreditSpecifications().getInterestPercentage())
                        .finalAmount(creditCreateReq.getCreditSpecifications().getAmount()+
                                creditCreateReq.getCreditSpecifications().getAmount()*
                                creditCreateReq.getCreditSpecifications().getInterestPercentage())
                        .amount(creditCreateReq.getCreditSpecifications().getAmount())
                        .build())
                .build();
    }

    @Override
    public CreditCreateRequest creditToCreditCreateRequest(CreditEntity credit) {
        return CreditCreateRequest.builder()
                .clientId(credit.getClient().getId())
                .creditSpecifications(credit.getCreditSpecifications())
                .build();
    }

    @Override
    public CreditEntity creditUpdateRequestToCredit(CreditUpdateRequest credit) {
        return CreditEntity.builder()
                .id(credit.getId())


                .creditSpecifications(credit.getCreditSpecifications())
                .build();
    }

    @Override
    public CreditUpdateRequest creditToCreditUpdateCreateRequest(CreditEntity credit) {
        return CreditUpdateRequest.builder()
                .id(credit.getId())
                .build();
    }

    @Override
    public CreditEntity creditFindBalancesResponseToCredit(CreditFindBalancesResponse credit) {
        return CreditEntity.builder()
                .id(credit.getId())
                .build();
    }

    @Override
    public CreditFindBalancesResponse creditToCreditFindBalancesResponse(CreditEntity credit) {
        return CreditFindBalancesResponse.builder()
                .id(credit.getId())
                .build();
    }

    @Override
    public CreditEntity fillCreditWithCreditUpdateRequest(CreditEntity credit, CreditUpdateRequest creditToUpdate) {
        credit.setId(creditToUpdate.getId());

        credit.setCreditSpecifications(creditToUpdate.getCreditSpecifications());
        return credit;
    }
}
