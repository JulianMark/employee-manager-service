package com.employee.manager.service.http;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeListResponse {

    @ApiModelProperty(notes="Resultado de la consulta ejecutada exito = lista de empleados")
    private List employeeList;
    @ApiModelProperty(notes="Resultado de la consulta ejecutada exito = mensaje de error")
    private String errorMessage;

    public EmployeeListResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public EmployeeListResponse(List employeeList) {
        this.employeeList = employeeList;
    }
}
