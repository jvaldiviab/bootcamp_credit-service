package com.project.creditservice.repository;


import com.project.creditservice.models.entity.CreditEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ICreditRepository extends ReactiveMongoRepository<CreditEntity, String> {
    Flux<CreditEntity> findCreditsByClientId(String id);
}
