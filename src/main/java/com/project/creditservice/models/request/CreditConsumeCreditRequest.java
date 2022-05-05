package com.project.creditservice.models.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreditConsumeCreditRequest {
    private String id;
    private Double amount;
}
