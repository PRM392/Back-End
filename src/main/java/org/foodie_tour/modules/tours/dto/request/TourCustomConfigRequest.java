package org.foodie_tour.modules.tours.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourCustomConfigRequest {

    private Boolean isCustomizable;
    private Integer totalCustomPlaces;
    private Integer minFoodPlaces;
    private Integer minVisitPlaces;
}
