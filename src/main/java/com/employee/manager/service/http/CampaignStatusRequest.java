package com.employee.manager.service.http;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class CampaignStatusRequest {
    @ApiModelProperty(notes="Numero de ID de campaña a consultar", required = true, example = "1")
    private Integer idCampaign;
}
