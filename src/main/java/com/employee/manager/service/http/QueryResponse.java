package com.employee.manager.service.http;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class QueryResponse {

    @ApiModelProperty(notes="Resultado de la consulta ejecutada exito = 0")
    private Byte result;
    @ApiModelProperty(notes="Mensaje de error, en caso de que falle el WS")
    private String errorMessage;

    public QueryResponse(Byte result) {
        this.result = result;
    }

    public QueryResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
