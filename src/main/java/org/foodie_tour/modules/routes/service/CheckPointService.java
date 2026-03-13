package org.foodie_tour.modules.routes.service;

import org.foodie_tour.modules.routes.dto.request.CheckPointRequest;
import org.foodie_tour.modules.routes.dto.response.CheckPointResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CheckPointService {

    CheckPointResponse createCheckPoint(CheckPointRequest checkPointRequest, List<MultipartFile> files, Integer primaryIndex) throws IOException;
    List<CheckPointResponse> getCheckpointsByTourId(Long tourId);
}
