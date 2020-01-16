package com.employee.manager.service;

import com.employee.manager.mapper.EmployeeListWithoutAssignmentMapper;
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
import static org.mockito.Mockito.when;

@DisplayName("Employees list manager service")
class ObtainEmployeesWithoutAssignmentManagerServiceTest {

    @Spy
    private List<EmployeeDTO> employeeDTOList;

    @Mock
    private EmployeeDTO employeeA;

    @Mock
    private EmployeeListWithoutAssignmentMapper employeeListWithoutAssignmentMapper;

    @Mock
    private ListValidator listValidator;

    @InjectMocks
    private ManagerService sut;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("When obtainEmployeeList is empty. Should return 204 (No Content)")
    public void obtainEmployeeListWithoutAssignment_EmployeeListIsEmpty_ReturnsNoContent(){
        employeeDTOList = Collections.emptyList();
        when(employeeListWithoutAssignmentMapper.obtainEmployeeListWithoutAssignment())
                .thenReturn(employeeDTOList);
        when(listValidator.obtainEmployeeListValidator())
                .thenReturn(employeeList -> ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new EmployeeListResponse(Collections.EMPTY_LIST)));

        ResponseEntity<EmployeeListResponse> responseEntity = sut.obtainEmployeeListWithoutAssignment();

        assertThat("Status Code Response", responseEntity.getStatusCode(),
                is(HttpStatus.NO_CONTENT));
        assertThat(responseEntity.getBody().getEmployeeList().toString(),
                is(employeeDTOList.toString()));
    }

    @Test
    @DisplayName("When EmployeeListWithoutAssignmentMapper ThrowsException. Should return 500 (Internal Server Error)")
    public void obtainEmployeeListWithoutAssignment_EmployeeListWithoutAssignmentMapperThrowsException_ReturnsInternalServerError(){
        when(employeeListWithoutAssignmentMapper.obtainEmployeeListWithoutAssignment())
                .thenReturn(null);

        ResponseEntity<EmployeeListResponse> responseEntity = sut.obtainEmployeeListWithoutAssignment();

        assertThat("Status Code Response",
                responseEntity.getStatusCode(),
                is(HttpStatus.INTERNAL_SERVER_ERROR ));
    }

    @Test
    @DisplayName("When obtainEmployeeListWithoutAssignment did not catch exceptions. Should return 200 (OK)")
    public void obtainEmployeeListWithoutAssignment_NoExceptionCaught_ReturnsOk(){
        employeeDTOList = Arrays.asList(employeeA);
        when(employeeListWithoutAssignmentMapper.obtainEmployeeListWithoutAssignment())
                .thenReturn(employeeDTOList);
        when(listValidator.obtainEmployeeListValidator())
                .thenReturn(employeeList -> ResponseEntity.status(HttpStatus.OK)
                        .body(new EmployeeListResponse(employeeList)));

        ResponseEntity<EmployeeListResponse> responseEntity = sut.obtainEmployeeListWithoutAssignment();

        assertThat("Status Code Response", responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getEmployeeList().get(0).toString(),
                is (employeeDTOList.get(0).toString()));
    }
}