package org.foodie_tour.modules.tours.service.Impl;

import lombok.RequiredArgsConstructor;
import org.foodie_tour.common.exception.DuplicateResourceException;
import org.foodie_tour.common.exception.InvalidateDataException;
import org.foodie_tour.common.exception.ResourceNotFoundException;
import org.foodie_tour.modules.aws.s3.service.S3Service;
import org.foodie_tour.modules.images.entity.DishImage;
import org.foodie_tour.modules.images.entity.Image;
import org.foodie_tour.modules.images.enums.DishImageStatus;
import org.foodie_tour.modules.images.enums.ImageStatus;
import org.foodie_tour.modules.images.repository.ImageRepository;
import org.foodie_tour.modules.routes.entity.CheckPoint;
import org.foodie_tour.modules.routes.repository.CheckPointRepository;
import org.foodie_tour.modules.tours.dto.request.DishRequest;
import org.foodie_tour.modules.tours.dto.response.DishResponse;
import org.foodie_tour.modules.tours.entity.Dish;
import org.foodie_tour.modules.tours.entity.Tour;
import org.foodie_tour.modules.tours.enums.DishStatus;
import org.foodie_tour.modules.tours.mapper.DishMapper;
import org.foodie_tour.modules.tours.repository.DishRepository;
import org.foodie_tour.modules.tours.repository.TourRepository;
import org.foodie_tour.modules.tours.service.DishService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;
    private final TourRepository tourRepository;
    private final ImageRepository imageRepository;
    private final S3Service s3Service;
    private final CheckPointRepository checkPointRepository;

    @Override
    @Transactional
    public DishResponse createDish(DishRequest dishRequest, List<MultipartFile> files) throws IOException {
        if (dishRepository.existsByDishName(dishRequest.getDishName())) {
            throw new DuplicateResourceException("Trùng tên món ăn đã tồn tại");
        }

        Tour tour = tourRepository.findById(dishRequest.getTourId())
                .orElseThrow(() -> new ResourceNotFoundException("Tour không tồn tại"));

        CheckPoint checkPoint = null;
        if (dishRequest.getCheckpointId() != null) {
            checkPoint = checkPointRepository.findById(dishRequest.getCheckpointId())
                    .orElseThrow(() -> new ResourceNotFoundException("Địa điểm này không tồn tại"));

            if (!checkPoint.getTour().getTourId().equals(tour.getTourId())) {
                throw new InvalidateDataException("Địa điểm phải thuộc về tour đã chọn");
            }
        }

        Dish dish = dishMapper.toEntity(dishRequest);
        dish.setTour(tour);
        dish.setCheckPoint(checkPoint);
        dish.setCreatedAt(LocalDateTime.now());
        dish.setDishStatus(DishStatus.ACTIVE);

        if (dish.getDishImages() == null) {
            dish.setDishImages(new ArrayList<>());
        }

        if (!CollectionUtils.isEmpty(files)) {
            for (int i = 0; i < files.size(); i++) {
                MultipartFile file = files.get(i);
                String publicUrl = s3Service.uploadFile(file);

                Image img = Image.builder()
                        .imageUrl(publicUrl)
                        .imageStatus(ImageStatus.ACTIVE)
                        .imageDescription("Ảnh cho món: " + dish.getDishName())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
                img = imageRepository.save(img);

                boolean isImgPrimary = (i == 0) && (dishRequest.getIsPrimary() != null && dishRequest.getIsPrimary());

                DishImage dishImage = DishImage.builder()
                        .dish(dish)
                        .image(img)
                        .status(DishImageStatus.ACTIVE)
                        .isPrimary(isImgPrimary)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();

                dish.getDishImages().add(dishImage);
            }
        }

        Dish saved = dishRepository.save(dish);
        return dishMapper.toResponse(saved);
    }
    @Override
    @Transactional(readOnly = true)
    public List<DishResponse> getAllDishes(DishStatus dishStatus) {
        List<Dish> dishes;
        if (dishStatus != null) {
            dishes = dishRepository.findByDishStatus(dishStatus);
        } else {
            dishes = dishRepository.findAll();
        }

        return dishes.stream()
                .map(dishMapper::toResponse)
                .toList();
    }

    @Override
    public DishResponse getDishById(Long dishId, DishStatus dishStatus) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new ResourceNotFoundException("Món ăn không tồn tại"));

        if (dishStatus != null && !dish.getDishStatus().equals(dishStatus)) {
            throw new ResourceNotFoundException("Món ăn không tồn tại với trạng thái");
        }
        return dishMapper.toResponse(dish);
    }

    @Override
    @Transactional
    public DishResponse updateDish(Long dishId, DishRequest dishRequest) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new ResourceNotFoundException("Món ăn không tồn tại"));

        dishMapper.updateEntity(dishRequest, dish);
        dish.setUpdatedAt(LocalDateTime.now());
        dish = dishRepository.save(dish);
        return dishMapper.toResponse(dish);
    }

    @Override
    public void deleteDish(Long dishId) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new ResourceNotFoundException("Món ăn không tồn tại"));
        dish.setDishStatus(DishStatus.INACTIVE);
        dishRepository.save(dish);
    }
}
