package com.employee.manager.utils.validators;

import com.employee.manager.model.dto.EmployeeDTO;
import com.employee.manager.service.http.EmployeeListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ListValidatorTest {

    @InjectMocks
    private ListValidator sut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("When the method receives a list is not empty should returns 200")
    public void obtainList_EmployeeListIsNotEmpty_ReturnsOK() {
        EmployeeDTO validEmployeeDTO = new EmployeeDTO(1, "JOHN", "DOU", "31252456", 1);
        List<EmployeeDTO> validList = Arrays.asList(validEmployeeDTO);

        ResponseEntity<EmployeeListResponse> responseEntity = sut.apply(validList);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getEmployeeList().get(0).toString(), is(validList.get(0).toString()));
        assertThat(responseEntity.getBody().getEmployeeList().size(), is(validList.size()));
    }

    @Test
    @DisplayName("When the method receives a empty list should returns 204")
    public void obtainList_EmployeeListIsEmpty_ReturnsNoContent() {

        ResponseEntity<EmployeeListResponse> responseEntity = sut.apply(Collections.EMPTY_LIST);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NO_CONTENT));
    }
}