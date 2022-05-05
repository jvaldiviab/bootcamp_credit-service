package com.project.creditservice.controller;

import com.project.creditservice.models.entity.CreditEntity;
import com.project.creditservice.models.request.CreditCreateRequest;
import com.project.creditservice.models.request.CreditUpdateRequest;
import com.project.creditservice.models.response.ClientClientServiceResponse;
import com.project.creditservice.service.ICreditService;
import com.project.creditservice.utilities.errors.ElementBlockedException;
import com.project.creditservice.utilities.errors.EnterpriseLogicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/account")
public class CreditController {

    public final ICreditService creditService;

    @PostMapping("/create")
    public Mono<ResponseEntity<CreditEntity>> createCredit(@RequestBody CreditCreateRequest creditDTO) {
        log.info("Post operation in /credits");
        return creditService.create(creditDTO)
                .flatMap(createdCredit -> Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(createdCredit)))
                .onErrorResume(ElementBlockedException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.LOCKED).build()))
                .onErrorResume(EnterpriseLogicException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()))
                .onErrorResume(IllegalArgumentException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()))
                .onErrorResume(NoSuchElementException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(null)));
    }

    @GetMapping("/credits")
    public Flux<CreditEntity> findAllCredits(){
        return creditService.findAll();
    }

    @GetMapping("/credits/{id}")
    public Mono<ResponseEntity<CreditEntity>> findCreditById(@PathVariable("id") String id) {
        log.info("Get operation in /credits/{}", id);
        return creditService.findById(id)
                .flatMap(retrievedCredit -> Mono.just(ResponseEntity.ok(retrievedCredit)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PutMapping("/credits")
    public Mono<ResponseEntity<CreditEntity>> updateCredit(@RequestBody CreditUpdateRequest creditDTO) {
        log.info("Put operation in /credits");
        return creditService.update(creditDTO)
                .flatMap(createdCredit -> Mono.just(ResponseEntity.ok(createdCredit)))
                .onErrorResume(ElementBlockedException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.LOCKED).build()))
                .onErrorResume(EnterpriseLogicException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()))
                .onErrorResume(IllegalArgumentException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()))
                .onErrorResume(NoSuchElementException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/credits/{id}")
    public Mono<ResponseEntity<CreditEntity>> deleteCredit(@PathVariable("id") String id) {
        return creditService.removeById(id)
                .flatMap(removedCredit -> Mono.just(ResponseEntity.ok(removedCredit)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("client/{id}/credits")
    public Flux<CreditEntity> findCreditsByClientId(@PathVariable("id") String id) {
        return creditService.findByClientId(id);
    }
    @GetMapping("client-service/{id}")
    public Mono<ResponseEntity<ClientClientServiceResponse>> findByIdClientService(@PathVariable("id") String id) {
        return creditService.findByIdClientService(id)
                .flatMap(retrievedCustomer -> Mono.just(ResponseEntity.ok(retrievedCustomer)));
    }

    // ## CASES
/*
    @PostMapping("/credits/transactions/consumes")
    public Mono<ResponseEntity<CreditEntity>> consumeCredit(@RequestBody CreditConsumeCreditRequest transaction) {
        log.info("Post operation in /credits/transactions/consumes");
        return creditService.consumeCredit(transaction)
                .flatMap(createdCredit -> Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(createdCredit)))
                .onErrorResume(ElementBlockedException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.LOCKED).build()))
                .onErrorResume(IllegalArgumentException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()))
                .onErrorResume(NoSuchElementException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(null)));
    }

    @PostMapping("/credits/operations/payments/{id}")
    public Mono<ResponseEntity<Credit>> payCredit(@PathVariable("id") String id) {
        log.info("Post operation in /credits/operation/payments");
        return creditService.payCredit(id)
                .flatMap(createdCredit -> Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(createdCredit)))
                .onErrorResume(NoSuchElementException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(null)));
    }

    @PostMapping("/credits/{id}/generate-billing-order")
    public Mono<ResponseEntity<Credit>> generateBillingOrder(@PathVariable("id") String creditId) {
        log.info("Post operation in /credits/operations/payments/generate-billing-order");
        return creditService.generateBillingOrder(creditId)
                .flatMap(createdCredit -> Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(createdCredit)))
                .onErrorResume(BusinessLogicException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()))
                .onErrorResume(NoSuchElementException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(null)));
    }

    @GetMapping("/credits/{id}/operations")
    public Flux<Operation> findOperationsByCreditId(@PathVariable("id") String id) {
        log.info("Get operation in /credits/{id}/operations", id);
        return creditService.findOperationsByCreditId(id);
    }

    @GetMapping("customers/{id}/credits/balance")
    public Flux<CreditFindBalancesResponseDTO> findBalancesByCustomerId(@PathVariable("id") String id) {
        log.info("Get operation in /customers/{}/credits/balance", id);
        return creditService.findBalancesByCustomerId(id);
    }


*/



}