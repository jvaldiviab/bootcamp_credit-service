package com.project.creditservice.models.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreditFindBalancesResponse {
    private String id;
    private Double fullGrantedAmount;
    private Double availableAmount;

}