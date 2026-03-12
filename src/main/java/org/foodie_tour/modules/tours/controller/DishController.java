package org.foodie_tour.modules.tours.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.foodie_tour.modules.tours.dto.request.DishRequest;
import org.foodie_tour.modules.tours.dto.response.DishResponse;
import org.foodie_tour.modules.tours.enums.DishStatus;
import org.foodie_tour.modules.tours.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DishResponse> createDish(
            @RequestPart("request") @Valid DishRequest dishRequest,
            @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) throws IOException {
        DishResponse response = dishService.createDish(dishRequest, files);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("")
    public ResponseEntity<List<DishResponse>> getAllDishes(
            @RequestParam(required = false)
            DishStatus dishStatus
    ) {
        List<DishResponse> response = dishService.getAllDishes(dishStatus);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishResponse> getDishById(@PathVariable Long id,
                                                    @RequestParam(required = false) DishStatus dishStatus) {
        DishResponse response = dishService.getDishById(id, dishStatus);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishResponse> updateDish(
            @PathVariable Long id,
            @Valid @RequestBody DishRequest dishRequest
    ) {
        DishResponse response = dishService.updateDish(id, dishRequest);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.ok("Xóa món ăn thành công");
    }
}
