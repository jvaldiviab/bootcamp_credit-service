package com.project.creditservice.models.response;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ClientClientServiceResponse {
    private String id;
    private ClientTypeClientServiceResponse clientType;;
    private String status;
    private PersonInfoClientServiceResponse personInfo;
    private EnterpriseInfoClientServiceResponse enterpriseInfo;
}