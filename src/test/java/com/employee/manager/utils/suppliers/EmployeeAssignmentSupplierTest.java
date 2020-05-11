package com.employee.manager.utils.suppliers;

import com.employee.manager.service.http.EmployeeAssignmentCampaignResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


class EmployeeAssignmentSupplierTest {

    @InjectMocks
    private EmployeeAssignmentSupplier sut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void get() {
        ResponseEntity<EmployeeAssignmentCampaignResponse> responseEntity = sut.get();
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NO_CONTENT));
    }
}