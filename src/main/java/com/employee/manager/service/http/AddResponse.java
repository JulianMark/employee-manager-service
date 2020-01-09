package com.employee.manager.service.http;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class AddResponse {

    private Byte result;
    private String errorMessage;

    public AddResponse(Byte result) {
        this.result = result;
    }

    public AddResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
