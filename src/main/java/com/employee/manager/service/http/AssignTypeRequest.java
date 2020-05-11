package com.employee.manager.service.http;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class AssignTypeRequest {
    @ApiModelProperty(notes = "Numero de ID de empleado a ingresar", required = true, example = "1")
    private Integer idEmployee;
    @ApiModelProperty(notes = "Numero de tipo de empleado a ingresar", required = true, example = "1")
    private Integer idType;
}
