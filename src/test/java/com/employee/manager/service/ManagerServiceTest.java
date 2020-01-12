package com.employee.manager.service;

import com.employee.manager.mapper.EmployeeListWithoutAssignmentMapper;
import com.employee.manager.model.dto.EmployeeDTO;
import com.employee.manager.service.http.EmployeesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Employees list manager service")
class ManagerServiceTest {

    @Spy
    private List<EmployeeDTO> employeeDTOList;

    @Mock
    private EmployeeDTO employeeA;

    @Mock
    private EmployeeDTO employeeB;

    @Mock
    private EmployeeListWithoutAssignmentMapper employeeListWithoutAssignmentMapper;

    @InjectMocks
    private ManagerService sut;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        when(employeeDTOList.stream()).thenReturn(Stream.of(employeeA,employeeB));
    }

    @Test
    @DisplayName("When obtainEmployeeList is empty. Should return 204 (No Content)")
    public void obtainEmployeeListWithoutAssignment_EmployeeListIsEmpty_ReturnsNoContent(){
        when(employeeListWithoutAssignmentMapper.obtainEmployeeListWithoutAssignment())
                .thenReturn(new ArrayList<>());

        ResponseEntity<EmployeesResponse> responseEntity = sut.obtainEmployeeListWithoutAssignment();

        assertThat("Status Code Response",
                responseEntity.getStatusCode(),
                is(HttpStatus.NO_CONTENT));
    }

    @Test
    @DisplayName("When EmployeeListWithoutAssignmentMapper ThrowsException. Should return 500 (Internal Server Error)")
    public void obtainEmployeeListWithoutAssignment_EmployeeListWithoutAssignmentMapperThrowsException_ReturnsInternalServerError(){
        when(employeeListWithoutAssignmentMapper.obtainEmployeeListWithoutAssignment())
                .thenReturn(null);

        ResponseEntity<EmployeesResponse> responseEntity = sut.obtainEmployeeListWithoutAssignment();

        assertThat("Status Code Response",
                responseEntity.getStatusCode(),
                is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    @DisplayName("When EmployeeListWithoutAssignmentMapper ThrowsException. Should return 200 (OK)")
    public void obtainEmployeeListWithoutAssignment_NoExceptionCaught_ReturnsOk(){
        when(employeeListWithoutAssignmentMapper.obtainEmployeeListWithoutAssignment())
                .thenReturn(employeeDTOList);

        ResponseEntity<EmployeesResponse> responseEntity = sut.obtainEmployeeListWithoutAssignment();

        assertThat("Status Code Response",
                responseEntity.getStatusCode(),
                is(HttpStatus.OK));
    }
}