package com.employee.manager.service.http;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class AddRequest {

    @ApiModelProperty(notes="Nombre del empleado a ingresar", required = true, example = "JOHN")
    private String name;
    @ApiModelProperty(notes="Apellido del empleado a ingresar", required = true, example = "DOU")
    private String lastName;
    @ApiModelProperty(notes="Nickname del empleado a ingresar", required = true, example = "JOHNNY")
    private String nickname;
    @ApiModelProperty(notes="Passwrod del empleado a ingresar", required = true, example = "ABC123456")
    private String password;
}
