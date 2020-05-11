package com.employee.manager.service;


import com.employee.manager.mapper.EmployeeListMapper;
import com.employee.manager.model.dto.EmployeeDTO;
import com.employee.manager.service.http.EmployeeListResponse;
import com.employee.manager.service.http.SearchRequest;
import com.employee.manager.utils.validators.ListValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Employees list manager service")
class SearchEmployeeServiceTest {

    private final SearchRequest VALID_SEARCH_REQUEST = new SearchRequest("PARAM");

    @Spy
    private List<EmployeeDTO> employeeDTOList;

    @Mock
    private EmployeeDTO employeeA;

    @Mock
    private EmployeeListMapper employeeListMapper;

    @Mock
    private ListValidator listValidator;

    @InjectMocks
    private ManagerService sut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("When searchRequest is null. Should return 400 (Bad Request)")
    public void obtainEmployeeList_SearchRequestIsNull_ReturnsBadRequest() {
        ResponseEntity<EmployeeListResponse> responseEntity = sut.obtainEmployeeList(null);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    @DisplayName("When obtainEmployeeList is empty. Should return 204 (No Content)")
    public void obtainEmployeeList_EmployeeListIsEmpty_ReturnsNoContent() {
        employeeDTOList = Collections.emptyList();

        when(employeeListMapper.obtainEmployeeList(any())).thenReturn(employeeDTOList);
        when(listValidator.apply(employeeDTOList))
                .thenReturn(ResponseEntity.noContent().build());

        ResponseEntity<EmployeeListResponse> employeeResponseEntity = sut.obtainEmployeeList(VALID_SEARCH_REQUEST);

        assertThat(employeeResponseEntity.getStatusCode(), is(HttpStatus.NO_CONTENT));
    }

    @Test
    @DisplayName("When EmployeeListMapper ThrowsException. Should return 500 (Internal Server Error)")
    public void searchEmployee_searchEmployeeThrowsException_ReturnsInternalServerError() {
        when(employeeListMapper.obtainEmployeeList(any(SearchRequest.class))).thenReturn(null);

        ResponseEntity<EmployeeListResponse> responseEntity = sut.obtainEmployeeList(VALID_SEARCH_REQUEST);

        assertThat("Status Code Response", responseEntity.getStatusCode(),
                is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    @DisplayName("When searchEmployee did not catch Exceptions. Should return 200 (OK)")
    public void searchEmployee_NoExceptionCaught_ReturnsOk() {
        employeeDTOList = Arrays.asList(employeeA);
        when(employeeListMapper.obtainEmployeeList(any(SearchRequest.class)))
                .thenReturn(employeeDTOList);
        when(listValidator.apply(employeeDTOList))
                .thenReturn(ResponseEntity.ok(new EmployeeListResponse(employeeDTOList)));

        ResponseEntity<EmployeeListResponse> responseEntity = sut.obtainEmployeeList(VALID_SEARCH_REQUEST);

        assertThat("Status Code Response", responseEntity.getStatusCode(),
                is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getEmployeeList().get(0).toString(),
                is(employeeDTOList.get(0).toString()));
    }
}