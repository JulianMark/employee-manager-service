package com.employee.manager.builder;

import com.employee.manager.model.EmployeeCampaign;
import com.employee.manager.model.dto.EmployeeCampaignDTO;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class EmployeeCampaignBuilder implements Function<EmployeeCampaignDTO, EmployeeCampaign> {

    @Override
    public EmployeeCampaign apply(EmployeeCampaignDTO dto) {

        final Float totalDonations = Optional.ofNullable(dto.getTotalDonations()).orElse(0f);
        final Float totalAmountDonations = Optional.ofNullable(dto.getTotalAmountDonations()).orElse(0f);
        final Float totalProductiveHours = Optional.ofNullable(dto.getTotalProductiveHours()).orElse(0f);

        final Float totalNonProductiveHours = Optional.ofNullable(dto.getTotalNonProductiveHours()).orElse(0f);
        final Float totalAverageCatchment = Optional.of(totalDonations)
                .filter(td -> td == 0f)
                .orElseGet(() -> new Float(totalDonations / totalProductiveHours));

        final Float totalAverageAmount = (totalAmountDonations == 0f)
                ? 0f
                : (totalAmountDonations / totalDonations);
        final Float totalAverageCreditType = (totalDonations == 0f || dto.getCreditType() == 0f)
                ? 0f
                : (dto.getCreditType() / totalDonations) * 100;

        return new EmployeeCampaign(dto.getId()
                , dto.getName()
                , dto.getLastName()
                , totalDonations
                , totalAmountDonations
                , totalProductiveHours
                , totalNonProductiveHours
                , totalAverageCatchment
                , totalAverageAmount
                , totalAverageCreditType
                , dto.getInitialDate()
                , dto.getFinalDate());
    }
}
