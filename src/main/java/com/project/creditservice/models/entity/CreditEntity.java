package com.project.creditservice.models.entity;


import com.project.creditservice.models.dto.Client;
import com.project.creditservice.models.dto.CreditSpecifications;
import com.project.creditservice.models.dto.Transaction;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(collection = "credits")
public class CreditEntity {
    @Id
    private String id;
    private Client client;
    private Date membershipDate;
    private CreditSpecifications creditSpecifications;
    private ArrayList<Transaction> transaction; // transaction on credit card
}