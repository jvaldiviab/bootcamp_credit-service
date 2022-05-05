package com.project.creditservice.models.request;

import com.project.creditservice.models.dto.CreditSpecifications;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreditUpdateRequest {
    private String id;
    private String status;
    private Double fullGrantedAmount;
    private Double availableAmount;
    private Date dueDate;
    private CreditSpecifications creditSpecifications;
}
