package org.foodie_tour.modules.routes.dto.response;

import lombok.Builder;
import lombok.Data;
import org.foodie_tour.modules.routes.enums.CheckPointType;

import java.util.List;

@Data
@Builder
public class CheckPointResponse {

    private Long checkpointId;
    private Long tourId;
    private String locationName;
    private String description;
    private int durationAtLocation;
    private CheckPointType type;
}
