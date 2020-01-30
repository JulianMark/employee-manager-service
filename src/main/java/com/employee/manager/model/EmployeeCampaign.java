package com.employee.manager.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCampaign {

    private Integer id;
    private String name;
    private String lastName;
    private Float totalDonations;
    private Float totalAmountDonations;
    private Float totalProductiveHours;
    private Float totalNonProductiveHours;
    private Float totalAverageCatchment;
    private Float totalAverageAmount;
    private Float totalAverageCreditType;
    private String initialDate;
    private String finalDate;
}
