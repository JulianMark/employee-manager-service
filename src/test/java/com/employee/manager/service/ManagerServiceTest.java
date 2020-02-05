package com.employee.manager.service;


import com.employee.manager.mapper.CampaignStatusMapper;
import com.employee.manager.service.http.CampaignEmployeesResponse;
import com.employee.manager.service.http.CampaignStatusRequest;
import com.employee.manager.utils.validators.CampaignEmployeesValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ManagerServiceTest {

    private CampaignStatusRequest VALID_REQUEST = new CampaignStatusRequest(1);

    @Mock
    private CampaignStatusMapper campaignStatusMapper;

    @Mock
    private CampaignEmployeesValidator CampaignEmployeesValidator;

    @InjectMocks
     private ManagerService sut;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    static class CampaignStatusRequestArgumentsSource implements ArgumentsProvider{

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                  null
                    ,new CampaignStatusRequest(0)
                    ,new CampaignStatusRequest(-1)
                    ,new CampaignStatusRequest(null)
            ).map(Arguments::of);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(CampaignStatusRequestArgumentsSource.class)
    @DisplayName("When CampaignStatusRequest is null or id is less that or equal to zero should return 400 (Bad Request)")
    public void obtainCampaignStatus_IsNullOrNullProperties_ReturnsBadRequest (CampaignStatusRequest request){
        ResponseEntity<CampaignEmployeesResponse> responseEntity = sut.obtainCampaignStatus(request);
        assertThat(responseEntity.getStatusCode(), is (HttpStatus.BAD_REQUEST));
    }

    @Test
    @DisplayName("When obtainEmployeeList is empty. Should return 204 (No Content)")
    public void obtainCampaignStatus_EmployeeListIsEmpty_ReturnsNoContent(){
        when(campaignStatusMapper.obtainCampaignEmployees(any(CampaignStatusRequest.class)))
                .thenReturn(Collections.EMPTY_LIST);
        when(CampaignEmployeesValidator.apply(any())).thenReturn(ResponseEntity.noContent().build());

        ResponseEntity<CampaignEmployeesResponse> responseEntity = sut.obtainCampaignStatus(VALID_REQUEST);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NO_CONTENT));
    }

    @Test
    @DisplayName("When campaignStatusMapper ThrowsException. Should return 500 (Internal Server Error)")
    public void obtainCampaignStatus_campaignStatusMapperThrowsException_ReturnsInternalServerError(){
        when(campaignStatusMapper.obtainCampaignEmployees(any(CampaignStatusRequest.class)))
                .thenReturn(null);

        ResponseEntity<CampaignEmployeesResponse> responseEntity = sut.obtainCampaignStatus(VALID_REQUEST);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }





}