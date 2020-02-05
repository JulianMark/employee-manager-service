package com.employee.manager.service;


import com.employee.manager.mapper.AssignTypeMapper;
import com.employee.manager.service.http.AssignTypeRequest;
import com.employee.manager.service.http.QueryResponse;
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

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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

    static class AssignTypeRequestArgumentsSource implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    null
                    ,new AssignTypeRequest(null,1)
                    ,new AssignTypeRequest(1,null)
                    ,new AssignTypeRequest(0,1)
                    ,new AssignTypeRequest(1,0)
                    ,new AssignTypeRequest(-1,1)
                    ,new AssignTypeRequest(1,-1)
            ).map(Arguments::of);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(AssignTypeRequestArgumentsSource.class)
    @DisplayName("When assign type request is null, or param throw illegal exceptions should return 400 (Bad Request)")
    void assignEmployee_RequestIsNullOrParamThrowsExceptions_ReturnsBadRequest(AssignTypeRequest request){
        ResponseEntity<QueryResponse> responseEntity = sut.assignTypeEmployee(request);
        assertThat("Status Code Response", responseEntity.getStatusCode(),
                is(HttpStatus.BAD_REQUEST));
    }

    @Test
    @DisplayName("When assignTypeMapper throws Exception should return 500 (Internal Server Error)")
    void assignEmployee_AssignTypeMapperThrowException_ReturnsInternalServerError(){
        doThrow(new RuntimeException()).when(assignTypeMapper).assignType(any());
        ResponseEntity<QueryResponse> responseEntity = sut.assignTypeEmployee(VALID_ASSIGN_TYPE_REQUEST);
        assertThat("Status Code Response", responseEntity.getStatusCode(),
                is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    @DisplayName("When assignEmployee did not catch exceptions. Should return 200 (OK)")
    void assignEmployee_NoExceptionCaught_ReturnsOK(){
        doNothing().when(assignTypeMapper).assignType(any());
        ResponseEntity<QueryResponse> responseEntity = sut.assignTypeEmployee(VALID_ASSIGN_TYPE_REQUEST);
        assertThat(responseEntity.getStatusCode(),is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getResult(),is((byte)0));
    }
}