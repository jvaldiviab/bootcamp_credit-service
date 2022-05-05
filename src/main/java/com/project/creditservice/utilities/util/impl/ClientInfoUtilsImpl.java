package com.project.creditservice.utilities.util.impl;

import com.project.creditservice.models.dto.Address;
import com.project.creditservice.models.dto.EnterpriseInfo;
import com.project.creditservice.models.dto.PersonInfo;
import com.project.creditservice.models.response.AddressClientServiceResponse;
import com.project.creditservice.models.response.EnterpriseInfoClientServiceResponse;
import com.project.creditservice.models.response.PersonInfoClientServiceResponse;
import com.project.creditservice.utilities.util.IClientInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientInfoUtilsImpl implements IClientInfoUtils {
    @Override
    public PersonInfo PersonInfoClientServiceResponseToPersonInfo(PersonInfoClientServiceResponse personInfo) {
        return PersonInfo.builder()
                .name(personInfo.getName())
                .lastname(personInfo.getLastname())
                .documentNumber(personInfo.getDocumentNumber())
                .mobileNumber(personInfo.getMobileNumber())
                .nationality(personInfo.getNationality())
                .address(AddressClientServiceResponseToAddress(personInfo.getAddress()))
                .email(personInfo.getEmail())
                .birthdate(personInfo.getBirthdate())
                .build();
    }

    @Override
    public PersonInfoClientServiceResponse PersonInfoToPersonInfoClientServiceResponse(PersonInfo personInfo) {
        return PersonInfoClientServiceResponse.builder()
                .name(personInfo.getName())
                .lastname(personInfo.getLastname())
                .documentNumber(personInfo.getDocumentNumber())
                .mobileNumber(personInfo.getMobileNumber())
                .nationality(personInfo.getNationality())
                .address(AddressToAddressClientServiceResponse(personInfo.getAddress()))
                .email(personInfo.getEmail())
                .birthdate(personInfo.getBirthdate())
                .build();
    }

    @Override
    public EnterpriseInfo EnterpriseInfoClientServiceResponseToEnterpriseInfo(EnterpriseInfoClientServiceResponse enterpriseInfo) {
        return EnterpriseInfo.builder()
                .name(enterpriseInfo.getName())
                .ruc(enterpriseInfo.getRuc())
                .address(AddressClientServiceResponseToAddress(enterpriseInfo.getAddress()))
                .build();

    }

    @Override
    public EnterpriseInfoClientServiceResponse EnterpriseInfoToEnterpriseInfoClientServiceResponse(EnterpriseInfo enterpriseInfo) {
        return EnterpriseInfoClientServiceResponse.builder()
                .name(enterpriseInfo.getName())
                .ruc(enterpriseInfo.getRuc())
                .address(AddressToAddressClientServiceResponse(enterpriseInfo.getAddress()))
                .build();
    }

    public Address AddressClientServiceResponseToAddress(AddressClientServiceResponse address) {
        return Address.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .country(address.getCountry())
                .extendedAddress(address.getExtendedAddress())
                .build();
    }

    public AddressClientServiceResponse AddressToAddressClientServiceResponse(Address address) {
        return AddressClientServiceResponse.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .country(address.getCountry())
                .extendedAddress(address.getExtendedAddress())
                .build();
    }
}
