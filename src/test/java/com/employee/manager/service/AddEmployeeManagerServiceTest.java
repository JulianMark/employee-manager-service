package com.employee.manager.service;

import com.employee.manager.mapper.AddMapper;
import com.employee.manager.service.http.AddRequest;
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

@DisplayName("Add employee manager service")
class AddEmployeeManagerServiceTest {

    private final AddRequest VALID_ADD_REQUEST = new AddRequest("JOHN",
            "DOE",
            "314343432",
            "JOHNNY",
            "123456");
    @Mock
    private AddMapper addMapper;

    @InjectMocks
    private ManagerService sut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @ParameterizedTest
    @ArgumentsSource(RequestArgumentsSource.class)
    @DisplayName("When employee request is null Or Empty should return 400 (Bad Request)")
    void addEmployee_PropertyRequestIsNullOrEmpty_ReturnsBadRequest2(AddRequest request) {
        ResponseEntity<QueryResponse> responseEntity = sut.addEmployee(request);
        assertThat("Status Code Response", responseEntity.getStatusCode(),
                is(HttpStatus.BAD_REQUEST));
    }

    @Test
    @DisplayName("When addMapper throws Exception should return 500 (Internal Server Error)")
    void addEmployee_AddMapperThrowException_ReturnsInternalServerError() {
        doThrow(new RuntimeException()).when(addMapper).addEmployee(any());
        ResponseEntity<QueryResponse> responseEntity = sut.addEmployee(VALID_ADD_REQUEST);
        assertThat("Status Code Response", responseEntity.getStatusCode(),
                is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    @DisplayName("When addEmployee did not catch exceptions. Should return 200 (OK)")
    void addEmployee_NoExceptionCaught_ReturnsOK() {
        doNothing().when(addMapper).addEmployee(any());
        ResponseEntity<QueryResponse> responseEntity = sut.addEmployee(VALID_ADD_REQUEST);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getResult(), is((byte) 0));
    }

    static class RequestArgumentsSource implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments>
        provideArguments(ExtensionContext extensionContext) {
            return Stream.of(null
                    , new AddRequest("", "", "", "", "")
                    , new AddRequest("JOHN", "", "", "", "")
                    , new AddRequest("JOHN", "DOU", "", "", "")
                    , new AddRequest("JOHN", "DOU", "31464981", "", "")
                    , new AddRequest("JOHN", "DOU", "31464981", "", "")
                    , new AddRequest("JOHN", "DOU", "31464981", "JOHNNY", ""))
                    .map(Arguments::of);

        }
    }
}