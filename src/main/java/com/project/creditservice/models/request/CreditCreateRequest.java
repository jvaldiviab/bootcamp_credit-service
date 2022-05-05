package com.project.creditservice.models.request;


import com.project.creditservice.models.dto.CreditSpecifications;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreditCreateRequest {
    private String clientId;
    private CreditSpecifications creditSpecifications;
}