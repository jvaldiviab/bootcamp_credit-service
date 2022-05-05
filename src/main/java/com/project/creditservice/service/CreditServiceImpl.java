package com.project.creditservice.service;

import com.project.creditservice.models.dto.Client;
import com.project.creditservice.models.dto.Transaction;
import com.project.creditservice.models.entity.CreditEntity;
import com.project.creditservice.models.request.CreditConsumeCreditRequest;
import com.project.creditservice.models.request.CreditCreateRequest;
import com.project.creditservice.models.request.CreditPaymentCreditRequest;
import com.project.creditservice.models.request.CreditUpdateRequest;
import com.project.creditservice.models.response.ClientClientServiceResponse;
import com.project.creditservice.models.response.CreditFindBalancesResponse;
import com.project.creditservice.repository.ICreditRepository;
import com.project.creditservice.utilities.constants.Constants;
import com.project.creditservice.utilities.errors.ClientNotFoundException;
import com.project.creditservice.utilities.errors.ElementBlockedException;
import com.project.creditservice.utilities.errors.EnterpriseLogicException;
import com.project.creditservice.utilities.util.IClientUtils;
import com.project.creditservice.utilities.util.ICreditUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditServiceImpl implements ICreditService {

    private final ICreditRepository creditRepository;
    private final Constants constants;
    private final ICreditUtils creditUtils;
    private final IClientUtils clientUtils;

    @Autowired
    ReactiveCircuitBreaker clientsServiceReactiveCircuitBreaker;

    // ############################### CRUD operations
    @Override
    public Mono<CreditEntity> create(CreditCreateRequest creditCreateReq) {
        if (creditCreateReq.getClientId() == null)
            return Mono.error(new IllegalArgumentException("No id specified for credit"));

        if (!creditCreateReq.getClientId().isBlank()) {
            Mono<CreditEntity> createdCredit = findByIdClientService(creditCreateReq.getClientId())
                    .flatMap(retrievedClient -> creditToCreateValidation(creditCreateReq, retrievedClient))
                    .flatMap(validatedClient -> {
                        Client client = clientUtils.clientClientServiceToClient(validatedClient);
                        CreditEntity creditToCreate = creditUtils.creditCreateRequestToCredit(creditCreateReq, client);

                        return creditRepository.insert(creditToCreate);
                    })
                    .switchIfEmpty(Mono.error(new NoSuchElementException("Client does not exist")));
            return createdCredit;
        } else {
            return Mono.error(new IllegalArgumentException("Credit does not contain customer id"));
        }
    }

    @Override
    public Mono<CreditEntity> findById(String id) {
        Mono<CreditEntity> retrievedCredit = creditRepository.findById(id);
        return retrievedCredit;
    }

    @Override
    public Flux<CreditEntity> findByClientId(String id) {
        Flux<CreditEntity> retrievedAccount = creditRepository.findCreditsByClientId(id);
        return retrievedAccount;
    }

    @Override
    public Flux<CreditEntity> findAll() {
        Flux<CreditEntity> retrievedCredit = creditRepository.findAll();
        return retrievedCredit;
    }

    @Override
    public Mono<CreditEntity> update(CreditUpdateRequest creditDTO) {
        Mono<CreditEntity> updatedCredit = findById(creditDTO.getId())
                .flatMap(retrievedCredit -> {
                    return creditToUpdateValidation(creditDTO, retrievedCredit);
                })
                .flatMap(validatedCredit -> {
                    CreditEntity creditToUpdate = creditUtils.fillCreditWithCreditUpdateRequest(validatedCredit, creditDTO);
                    Mono<CreditEntity> nestedUpdatedCredit = creditRepository.save(creditToUpdate);
                    return nestedUpdatedCredit;
                })
                .switchIfEmpty(Mono.error(new NoSuchElementException("Credit does not exist")));

        return updatedCredit;
    }

    @Override
    public Mono<CreditEntity> removeById(String id) {
        Mono<CreditEntity> removedAccount = findById(id)
                .flatMap(retrievedAccount -> creditRepository.deleteById(retrievedAccount.getId()).thenReturn(retrievedAccount));
        return removedAccount;
    }

    // ############################### EXTERNAL operations
    @Override
    public Mono<ClientClientServiceResponse> findByIdClientService(String id) {
        String url = constants.getServicesPrefix() + constants.getServicesUrlGateway()
                + constants.getServicesPathClient() + "/getById/" + id;

        return clientsServiceReactiveCircuitBreaker.run(
                WebClient.create(url)
                        .get()
                        .retrieve()
                        .bodyToMono(ClientClientServiceResponse.class),
                throwable -> {
                    // return Mono.error(new CircuitBreakerException("CLIENT-SERVICE NO AVAILABLE"));
                    return Mono.just(new ClientClientServiceResponse());
                });
    }

    // ############################### USE CASE operations
    @Override
    public Mono<CreditEntity> consumeCredit(CreditConsumeCreditRequest consumeCreditReq) {
        return null;
    }

    @Override
    public Mono<CreditEntity> payCredit(CreditPaymentCreditRequest paymentCreditReq) {
        return null;
    }

    @Override
    public Flux<Transaction> findTransactionByCreditId(String creditId) {
        return null;
    }

    // ############################### HELPERS operations


    public Flux<CreditFindBalancesResponse> findBalancesByCustomerId(String id) {
        Flux<CreditFindBalancesResponse> retrievedBalances = findByClientId(id)
                .map(creditUtils::creditToCreditFindBalancesResponse);
        return retrievedBalances;
    }

    private Mono<ClientClientServiceResponse> creditToCreateValidation(CreditCreateRequest creditForCreate, ClientClientServiceResponse clientFromMicroservice) {

        if (clientFromMicroservice.getId() == null)
            return Mono.error(new ClientNotFoundException("The client id is null or does not exist"));

        if (clientFromMicroservice.getStatus().contentEquals(constants.getStatusBlocked()))
            return Mono.error(new ElementBlockedException("The client have blocked status"));

        if (clientFromMicroservice.getClientType().getDescription().contentEquals(constants.getClientPersonal())) {
            return findByClientId(clientFromMicroservice.getId())
                    .hasElements()
                    .flatMap(haveAnAccount -> {
                        if (Boolean.TRUE.equals(haveAnAccount))
                            return Mono.error(new EnterpriseLogicException("Client already have one credit"));
                        else
                            return Mono.just(clientFromMicroservice);
                    });
        } else {
            return Mono.just(clientFromMicroservice);
        }
    }

    private Mono<CreditEntity> creditToUpdateValidation(CreditUpdateRequest creditForUpdate, CreditEntity creditInDatabase) {
        if (creditInDatabase.getClient().getStatus().contentEquals(constants.getStatusBlocked())) {
            return Mono.error(new ElementBlockedException("The customer have blocked status"));
        }
        return Mono.just(creditInDatabase);
    }
}