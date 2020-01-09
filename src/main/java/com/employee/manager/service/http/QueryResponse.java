package com.employee.manager.service.http;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class QueryResponse {
    private Byte result;
    private String errorMessage;

    public QueryResponse(Byte result) {
        this.result = result;
    }

    public QueryResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
