package com.employee.manager.service.http;

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

    private String name;
    private String lastName;
    private String nickname;
    private String password;
}
