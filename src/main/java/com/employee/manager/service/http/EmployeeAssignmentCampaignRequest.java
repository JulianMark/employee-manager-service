package com.employee.manager.service.http;

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
public class EmployeeAssignmentCampaignRequest {

    private Integer idType;
    private Integer idCampaign;

}
