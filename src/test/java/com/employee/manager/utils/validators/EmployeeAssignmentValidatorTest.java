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

    private static ResponseEntity<EmployeeAssignmentCampaignResponse> get (){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new EmployeeAssignmentCampaignResponse());
    }

    @InjectMocks
    private EmployeeAssignmentValidator sut;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void obtainList_EmployeeListIsNotEmpty_ReturnsOK (){
        EmployeeAssignmentCampaignResponse response = new EmployeeAssignmentCampaignResponse(
                1,"JUAN","PEREZ","31675589",null);

        ResponseEntity<EmployeeAssignmentCampaignResponse> responseEntity = sut.apply(response);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getId(), is(response.getId()));
    }

    @Test
    public void obtainEmptyEmployeeAssignment_EmployeeIsEmpty_ReturnsNoContent(){
        Supplier<ResponseEntity<EmployeeAssignmentCampaignResponse>> expected = EmployeeAssignmentValidatorTest::get;
        //Supplier<ResponseEntity<EmployeeAssignmentCampaignResponse>> actual = sut.obtainEmptyEmployeeAssignment();

        //assertThat(expected.get().toString(), is (actual.get().toString()));
    }

}