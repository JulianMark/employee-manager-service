package com.employee.manager.utils.validators;

import com.employee.manager.model.dto.EmployeeDTO;
import com.employee.manager.service.http.EmployeeListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Supplier;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ListValidatorTest {

    @InjectMocks
    private ListValidator sut;

    private static ResponseEntity<EmployeeListResponse> get() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new EmployeeListResponse(Collections.emptyList()));
    }

    @BeforeEach
    public void setUp(){ MockitoAnnotations.initMocks(this); }

    @Test
    public void obtainList_EmployeeListIsNotEmpty_ReturnsOK (){
        ResponseEntity<EmployeeListResponse> responseEntity = sut.obtainList(Arrays.asList(new EmployeeDTO()));
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void obtainEmptyList_EmployeeListIsEmpty_ReturnsNoContent (){
        Supplier<ResponseEntity<EmployeeListResponse>> expected = ListValidatorTest::get;
        Supplier<ResponseEntity<EmployeeListResponse>> actual = sut.obtainEmptyList();
        
        assertThat(expected.get().toString(), is(actual.get().toString()));
    }




}