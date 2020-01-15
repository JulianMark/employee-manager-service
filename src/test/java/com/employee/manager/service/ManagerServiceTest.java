package com.employee.manager.service;

import com.employee.manager.mapper.EmployeeAssignmentCampaignMapper;
import com.employee.manager.service.http.EmployeeAssignmentCampaignRequest;
import com.employee.manager.service.http.EmployeeAssignmentCampaignResponse;
import com.employee.manager.service.http.EmployeeListResponse;
import com.employee.manager.service.http.QueryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ManagerServiceTest {

    private final EmployeeAssignmentCampaignRequest VALID_REQUEST = new EmployeeAssignmentCampaignRequest(2,1);

    @Mock
    private EmployeeAssignmentCampaignMapper employeeAssignmentCampaignMapper;

    @InjectMocks
    private ManagerService sut;

    @BeforeEach
    public void setUp(){ MockitoAnnotations.initMocks(this); }

    @Test
    @DisplayName("When EmployeeAssignmentCampaignRequest is null should return 400 (Bad Request)")
    void obtainEmployeeAssignmentCampaign__RequestIsNull_ReturnsBadRequest(){
        ResponseEntity<EmployeeAssignmentCampaignResponse> responseEntity = sut
                .obtainEmployeeAssignmentCampaign(null);

        assertThat("Status Code Response", responseEntity.getStatusCode(),
                is(HttpStatus.BAD_REQUEST));
    }

    @Test
    @DisplayName("When EmployeeAssignmentCampaignRequest IdType is null should return 400 (Bad Request)")
    void obtainEmployeeAssignmentCampaign__RequestIdTypeIsNull_ReturnsBadRequest(){
        EmployeeAssignmentCampaignRequest request = new EmployeeAssignmentCampaignRequest(null,1);

        ResponseEntity<EmployeeAssignmentCampaignResponse> responseEntity = sut
                .obtainEmployeeAssignmentCampaign(request);

        assertThat("Status Code Response", responseEntity.getStatusCode(),
                is(HttpStatus.BAD_REQUEST));
    }

    @Test
    @DisplayName("When EmployeeAssignmentCampaignRequest IdType is null should return 400 (Bad Request)")
    void obtainEmployeeAssignmentCampaign__RequestIdCampaignIsNull_ReturnsBadRequest(){
        EmployeeAssignmentCampaignRequest request = new EmployeeAssignmentCampaignRequest(2,null);

        ResponseEntity<EmployeeAssignmentCampaignResponse> responseEntity = sut
                .obtainEmployeeAssignmentCampaign(request);

        assertThat("Status Code Response", responseEntity.getStatusCode(),
                is(HttpStatus.BAD_REQUEST));
    }

    @Test
    @DisplayName("When obtainEmployeeList is empty. Should return 204 (No Content)")
    public void obtainEmployeeListWithoutAssignment_EmployeeListIsEmpty_ReturnsNoContent(){
        when(employeeAssignmentCampaignMapper.obtainEmployeeAssignmentCampaign(any()))
                .thenReturn(null);

        ResponseEntity<EmployeeAssignmentCampaignResponse> responseEntity = sut
                .obtainEmployeeAssignmentCampaign(VALID_REQUEST);

        assertThat("Status Code Response", responseEntity.getStatusCode(),
                is(HttpStatus.NO_CONTENT));
    }

    @Test
    @DisplayName("When EmployeeMapper throws Exception.Should return 500 (Internal Server Error)")
    public void loginEmployee_EmployeeMapperThrowException_ReturnsInternalServerError(){
        when(employeeAssignmentCampaignMapper.obtainEmployeeAssignmentCampaign(any()))
                .thenThrow(new RuntimeException ("something bad happened"));

        ResponseEntity<EmployeeAssignmentCampaignResponse> responseEntity = sut
                .obtainEmployeeAssignmentCampaign(VALID_REQUEST);

        assertThat("Status Code Response", responseEntity.getStatusCode(),
                is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    @DisplayName("When obtainEmployeeAssignmentCampaign did not catch exceptions. Should return 200 (OK)")
    public void obtainEmployeeAssignmentCampaign_NoExceptionCaught_ReturnsOk(){
        when(employeeAssignmentCampaignMapper.obtainEmployeeAssignmentCampaign(any()))
                .thenReturn(new EmployeeAssignmentCampaignResponse("test"));

        ResponseEntity<EmployeeAssignmentCampaignResponse> responseEntity = sut
                .obtainEmployeeAssignmentCampaign(VALID_REQUEST);

        assertThat("Status Code Response", responseEntity.getStatusCode(),
                is(HttpStatus.OK));
    }

}