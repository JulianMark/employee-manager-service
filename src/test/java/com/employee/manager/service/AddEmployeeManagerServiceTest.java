package com.employee.manager.service;

import com.employee.manager.mapper.AddMapper;
import com.employee.manager.service.http.AddRequest;
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

@DisplayName("Add employee manager service")
class AddEmployeeManagerServiceTest {

    private final AddRequest VALID_ADD_REQUEST = new AddRequest("JOHN",
                                                            "DOE",
                                                            "JOHNNY",
                                                            "123456");
    @Mock
    private AddMapper addMapper;

    @InjectMocks
    private ManagerService sut;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("When employee request is null should return 400 (Bad Request)")
    void addEmployee_RequestIsNull_ReturnsBadRequest(){
        ResponseEntity<QueryResponse> responseEntity = sut.addEmployee(null);
        assertThat("Status Code Response",
                responseEntity.getStatusCode(),
                is(HttpStatus.BAD_REQUEST));
    }

    @Test
    @DisplayName("When employee property request is null or empty should return 400 (Bad Request)")
    void addEmployee_PropertyRequestIsNullOrEmpty_ReturnsBadRequest(){
        AddRequest addRequestWithEmptyProp = new AddRequest("","","","");
        ResponseEntity<QueryResponse> responseEntity = sut.addEmployee(addRequestWithEmptyProp);
        assertThat("Status Code Response",
                responseEntity.getStatusCode(),
                is(HttpStatus.BAD_REQUEST));
    }

    @Test
    @DisplayName("When addMapper throws Exception should return 500 (Internal Server Error)")
    void addEmployee_AddMapperThrowException_ReturnsInternalServerError(){
        doThrow(new RuntimeException()).when(addMapper).addEmployee(any());
        ResponseEntity<QueryResponse> responseEntity = sut.addEmployee(VALID_ADD_REQUEST);
        assertThat("Status Code Response",
                responseEntity.getStatusCode(),
                is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    @DisplayName("When no Exception is caught should return 200 (OK)")
    void addEmployee_NoExceptionCaught_ReturnsOK(){
        doNothing().when(addMapper).addEmployee(any());
        ResponseEntity<QueryResponse> responseEntity = sut.addEmployee(VALID_ADD_REQUEST);
        assertThat(responseEntity.getStatusCode(),is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getResult(),is(not(nullValue())));
    }



}