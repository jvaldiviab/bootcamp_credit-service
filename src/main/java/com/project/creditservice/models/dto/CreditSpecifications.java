package com.project.creditservice.models.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreditSpecifications {
    private int closingDay; // día de pago
    private int numberInstallments; // numero de cuotas
    private Double interestPercentage; // intereses
    private Double finalAmount; // monto final
    private Double amount; // monto

}
