package com.employee.manager.service;


import com.employee.manager.mapper.EmployeeListMapper;
import com.employee.manager.model.dto.EmployeeDTO;
import com.employee.manager.service.http.EmployeeListResponse;
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
class SearchEmployeeWithParamManagerServiceTest {

    private final String VALID_PARAM = "PARAM";
    private final String NO_RESULT_PARAM = "";

    @Spy
    private List<EmployeeDTO> employeeDTOList;

    @Mock
    private EmployeeDTO employeeA;

    @Mock
    private EmployeeDTO employeeB;

    @Mock
    private EmployeeListMapper employeeListMapper;

    @Mock
    private ListValidator listValidator;

    @InjectMocks
    private ManagerService sut;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

/*    @Test
    @DisplayName("When searchEmployee is empty. Should return 204 (No Content)")
    public void searchEmployee_EmployeeListIsEmpty_ReturnsNoContent() {
        employeeDTOList = Collections.emptyList();

        when(employeeListMapper.obtainEmployeeList(any())).thenReturn(employeeDTOList);
        when(listValidator.obtainEmployeeListValidator())
                .thenReturn(employeeList -> ResponseEntity
                        .status(HttpStatus.NO_CONTENT).body(new EmployeeListResponse(Collections.emptyList())));

        ResponseEntity<EmployeeListResponse> employeeResponseEntity = sut.searchEmployee(VALID_PARAM);

        assertThat(employeeResponseEntity.getStatusCode(), is(HttpStatus.NO_CONTENT));
        assertThat(employeeResponseEntity.getBody().getEmployeeList().toString(), is(employeeDTOList.toString()));

    }*/

  /*  @Test
    @DisplayName("When EmployeeListMapper ThrowsException. Should return 500 (Internal Server Error)")
    public void searchEmployee_searchEmployeeThrowsException_ReturnsInternalServerError(){
        when(employeeListMapper.obtainEmployeeList(any(String.class))).thenReturn(null);

        ResponseEntity<EmployeeListResponse> responseEntity = sut.searchEmployee(NO_RESULT_PARAM);

        assertThat("Status Code Response",responseEntity.getStatusCode(),
                is(HttpStatus.INTERNAL_SERVER_ERROR ));
    }

    @Test
    @DisplayName("When searchEmployee did not catch Exceptions. Should return 200 (OK)")
    public void searchEmployee_NoExceptionCaught_ReturnsOk(){
        employeeDTOList = Arrays.asList(employeeA,employeeB);
        when(employeeListMapper.obtainEmployeeList(any(String.class)))
                .thenReturn(employeeDTOList);
        when(listValidator.obtainEmployeeListValidator())
                .thenReturn(employeeList -> ResponseEntity
                .status(HttpStatus.OK).body(new EmployeeListResponse(employeeDTOList)));

        ResponseEntity<EmployeeListResponse> responseEntity = sut.searchEmployee(VALID_PARAM);

        assertThat("Status Code Response", responseEntity.getStatusCode(),
                is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getEmployeeList().get(0).toString(),
                is(employeeDTOList.get(0).toString()));
    }*/
}