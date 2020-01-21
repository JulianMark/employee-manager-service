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
public class EmployeeAssignmentCampaignResponse {

    private Integer id;
    private String name;
    private String lastName;
    private String dni;
    private String errorMessage;

    public EmployeeAssignmentCampaignResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
