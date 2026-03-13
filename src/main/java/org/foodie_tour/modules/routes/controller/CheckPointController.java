package org.foodie_tour.modules.routes.controller;

import lombok.RequiredArgsConstructor;
import org.foodie_tour.modules.routes.dto.request.CheckPointRequest;
import org.foodie_tour.modules.routes.dto.response.CheckPointResponse;
import org.foodie_tour.modules.routes.service.CheckPointService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/checkpoints")
@RequiredArgsConstructor
public class CheckPointController {

    private final CheckPointService checkPointService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CheckPointResponse> createCheckPoint(
            @RequestPart("request") CheckPointRequest request,
            @RequestPart(value = "files", required = false) List<MultipartFile> files,
            @RequestParam(value = "primaryIndex", required = false) Integer primaryIndex //up bao nhiêu tấm, muốn tấm nào hiển thị chính thì truyền số tương ứng
    ) throws IOException {
        CheckPointResponse response = checkPointService.createCheckPoint(request, files, primaryIndex);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/tour/{tourId}")
    public ResponseEntity<List<CheckPointResponse>> getCheckpointByTourId(
            @PathVariable Long tourId
    ) {
        List<CheckPointResponse> responses = checkPointService.getCheckpointsByTourId(tourId);
        return ResponseEntity.ok(responses);
    }
}
