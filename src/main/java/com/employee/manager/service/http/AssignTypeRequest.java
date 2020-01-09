package com.employee.manager.service.http;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class AssignTypeRequest {
    private Integer idEmployee;
    private Integer idType;
}
