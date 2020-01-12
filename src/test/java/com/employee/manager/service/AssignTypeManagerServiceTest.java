package com.employee.manager.service;


import com.employee.manager.mapper.AssignTypeMapper;
import com.employee.manager.service.http.AssignTypeRequest;
import com.employee.manager.service.http.QueryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@DisplayName("Assign type employee manager service")
class AssignTypeManagerServiceTest {

    private final AssignTypeRequest VALID_ASSIGN_TYPE_REQUEST = new AssignTypeRequest(1,1);

    @Mock
    private AssignTypeMapper assignTypeMapper;

    @InjectMocks
    private ManagerService sut;

    @BeforeEach
    void setUp(){ MockitoAnnotations.initMocks(this); }

    @Test
    @DisplayName("When assign type request is null should return 400 (Bad Request)")
    void assignEmployee_RequestIsNull_ReturnsBadRequest(){
        ResponseEntity<QueryResponse> responseEntity = sut.assignTypeEmployee(null);
        assertThat("Status Code Response",
                responseEntity.getStatusCode(),
                is(HttpStatus.BAD_REQUEST));
    }

    @Test
    @DisplayName("When assign property request is null or less than zero should return 400 (Bad Request)")
    void assignEmployee_PropertyRequestIsNullOrLessThanZero_ReturnsBadRequest(){
        AssignTypeRequest assignTypeRequestWithEmptyProp = new AssignTypeRequest(null,0);
        ResponseEntity<QueryResponse> responseEntity = sut.assignTypeEmployee(assignTypeRequestWithEmptyProp);
        assertThat("Status Code Response",
                responseEntity.getStatusCode(),
                is(HttpStatus.BAD_REQUEST));
    }

    @Test
    @DisplayName("When assignTypeMapper throws Exception should return 500 (Internal Server Error)")
    void assignEmployee_AssignTypeMapperThrowException_ReturnsInternalServerError(){
        doThrow(new RuntimeException()).when(assignTypeMapper).assignType(any());
        ResponseEntity<QueryResponse> responseEntity = sut.assignTypeEmployee(VALID_ASSIGN_TYPE_REQUEST);
        assertThat("Status Code Response",
                responseEntity.getStatusCode(),
                is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    @DisplayName("When assignEmployee did not catch exceptions. Should return 200 (OK)")
    void assignEmployee_NoExceptionCaught_ReturnsOK(){
        doNothing().when(assignTypeMapper).assignType(any());
        ResponseEntity<QueryResponse> responseEntity = sut.assignTypeEmployee(VALID_ASSIGN_TYPE_REQUEST);
        assertThat(responseEntity.getStatusCode(),is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getResult(),is(not(nullValue())));
    }
}