package com.employee.manager.service.http;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class SearchRequest {

    @ApiModelProperty(notes = "Nombre o apellido, o dni del, o los  empleados a buscar", required = true, example = "JOHN")
    private String param;
}
