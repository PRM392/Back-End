package org.foodie_tour.modules.routes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.foodie_tour.modules.routes.enums.CheckPointType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckPointRequest {

    private Long tourId;
    private String locationName;
    private String description;
    private int durationAtLocation;
    private String ggmapUrl;
    private CheckPointType type;
}
