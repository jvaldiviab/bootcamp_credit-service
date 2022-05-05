package com.project.creditservice.service;

import com.project.creditservice.models.dto.Transaction;
import com.project.creditservice.models.entity.CreditEntity;
import com.project.creditservice.models.request.CreditConsumeCreditRequest;
import com.project.creditservice.models.request.CreditCreateRequest;
import com.project.creditservice.models.request.CreditPaymentCreditRequest;
import com.project.creditservice.models.request.CreditUpdateRequest;
import com.project.creditservice.models.response.ClientClientServiceResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditService {

    // ###############################
    Mono<CreditEntity> create(CreditCreateRequest creditCreateReq);
    Mono<CreditEntity> findById(String creditId);
    Flux<CreditEntity> findByClientId(String clientId);
    Flux<CreditEntity> findAll();
    Mono<CreditEntity> update(CreditUpdateRequest creditUpdateReq);
    Mono<CreditEntity> removeById(String creditId);

    // ###############################
    Mono<ClientClientServiceResponse> findByIdClientService(String clientId);

    // ###############################
    Mono<CreditEntity> consumeCredit(CreditConsumeCreditRequest consumeCreditReq);
    Mono<CreditEntity> payCredit(CreditPaymentCreditRequest paymentCreditReq);

    Flux<Transaction> findTransactionByCreditId(String creditId);



}