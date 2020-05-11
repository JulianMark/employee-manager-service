package com.employee.manager.service.http;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CampaignEmployeesResponse {

    @ApiModelProperty(notes = "Resultado de la consulta ejecutada exito = lista de empleados")
    private List employeeList;
    @ApiModelProperty(notes = "Resultado de la consulta ejecutada exito = mensaje de error")
    private String errorMessage;

    public CampaignEmployeesResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public CampaignEmployeesResponse(List employeeList) {
        this.employeeList = employeeList;
    }
}
