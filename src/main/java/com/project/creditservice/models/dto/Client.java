package com.project.creditservice.models.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Client {
    private String id;
    private ClientType clientType;
    private String status;
    private PersonInfo personInfo; // information personal
    private EnterpriseInfo enterpriseInfo;
}