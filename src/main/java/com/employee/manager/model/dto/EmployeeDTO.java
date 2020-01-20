package com.employee.manager.model.dto;

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
public class EmployeeDTO {

    private Integer id;
    private String name;
    private String lastName;
    private String dni;
    private Integer idType;
    private String nameCampaign;
    private String descriptionOSC;

    public EmployeeDTO(Integer id, String name, String lastName, String dni, Integer idType) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.idType = idType;
    }
}
