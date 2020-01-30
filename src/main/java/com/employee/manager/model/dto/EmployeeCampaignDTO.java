package com.employee.manager.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCampaignDTO {

    private Integer id;
    private String name;
    private String lastName;
    private Float totalDonations;
    private Float totalAmountDonations;
    private Float creditType;
    private Float totalProductiveHours;
    private Float totalNonProductiveHours;
    private String initialDate;
    private String finalDate;
}
