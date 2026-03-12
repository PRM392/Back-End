package org.foodie_tour.modules.routes.service.impl;

import lombok.RequiredArgsConstructor;
import org.foodie_tour.common.exception.ResourceNotFoundException;
import org.foodie_tour.modules.aws.s3.service.S3Service;
import org.foodie_tour.modules.images.entity.CheckPointImage;
import org.foodie_tour.modules.images.entity.Image;
import org.foodie_tour.modules.images.enums.CheckPointImageStatus;
import org.foodie_tour.modules.images.enums.ImageStatus;
import org.foodie_tour.modules.images.repository.ImageRepository;
import org.foodie_tour.modules.routes.dto.request.CheckPointRequest;
import org.foodie_tour.modules.routes.dto.response.CheckPointResponse;
import org.foodie_tour.modules.routes.entity.CheckPoint;
import org.foodie_tour.modules.routes.mapper.CheckPointMapper;
import org.foodie_tour.modules.routes.repository.CheckPointRepository;
import org.foodie_tour.modules.routes.service.CheckPointService;
import org.foodie_tour.modules.tours.entity.Tour;
import org.foodie_tour.modules.tours.repository.TourRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckPointServiceImpl implements CheckPointService {


    private final TourRepository tourRepository;
    private final CheckPointMapper checkPointMapper;
    private final CheckPointRepository checkPointRepository;
    private final S3Service s3Service;
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public CheckPointResponse createCheckPoint(CheckPointRequest checkPointRequest, List<MultipartFile> files) throws IOException {
        Tour tour = tourRepository.findById(checkPointRequest.getTourId())
                .orElseThrow(() -> new ResourceNotFoundException("Tour không tồn tại"));

        CheckPoint checkPoint = checkPointMapper.toEntity(checkPointRequest);
        checkPoint.setTour(tour);
        CheckPoint saved = checkPointRepository.save(checkPoint);

        if (files != null && !files.isEmpty()) {
            List<CheckPointImage> cpImages = new ArrayList<>();
            for (MultipartFile file : files) {
                String publicUrl = s3Service.uploadFile(file);

                Image img = new Image();
                img.setImageUrl(publicUrl);
                img.setImageStatus(ImageStatus.ACTIVE);
                img = imageRepository.save(img);

                cpImages.add(CheckPointImage.builder()
                        .checkpoint(saved)
                        .image(img)
                        .status(CheckPointImageStatus.ACTIVE)
                        .build());
            }
            saved.setCheckpointImages(cpImages);
            checkPointRepository.save(saved);
        }
        return checkPointMapper.toResponse(saved);
    }
}
