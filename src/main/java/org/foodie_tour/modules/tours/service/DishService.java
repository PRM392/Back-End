package org.foodie_tour.modules.tours.service;

import org.foodie_tour.modules.tours.dto.request.DishRequest;
import org.foodie_tour.modules.tours.dto.response.DishResponse;
import org.foodie_tour.modules.tours.enums.DishStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DishService {

    DishResponse createDish(DishRequest dishRequest, List<MultipartFile> files) throws IOException;
    List<DishResponse> getAllDishes(DishStatus dishStatus);
    DishResponse getDishById(Long dishId, DishStatus dishStatus);
    DishResponse updateDish(Long dishId, DishRequest dishRequest);
    void deleteDish(Long dishId);
}
