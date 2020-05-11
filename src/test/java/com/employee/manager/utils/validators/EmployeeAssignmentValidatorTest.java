package com.employee.manager.utils.validators;

import com.employee.manager.service.http.EmployeeAssignmentCampaignResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class EmployeeAssignmentValidatorTest {

    @InjectMocks
    private EmployeeAssignmentValidator sut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void obtainList_EmployeeListIsNotEmpty_ReturnsOK() {
        EmployeeAssignmentCampaignResponse response = new EmployeeAssignmentCampaignResponse(
                1, "JUAN", "PEREZ", "31675589", null);

        ResponseEntity<EmployeeAssignmentCampaignResponse> responseEntity = sut.apply(response);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getId(), is(response.getId()));
    }

}