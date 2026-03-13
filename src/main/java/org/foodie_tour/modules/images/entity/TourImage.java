package org.foodie_tour.modules.images.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.foodie_tour.modules.images.enums.TourImageStatus;
import org.foodie_tour.modules.tours.entity.Tour;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tour_images")
@Builder
public class TourImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_image_id")
    private Long tourImageId;

    @Builder.Default
    @Column(name = "is_primary")
    private Boolean isPrimary = false;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "display_order")
    private int displayOrder;

    @Builder.Default
    @Column(name = "tour_image_status")
    @Enumerated(EnumType.STRING)
    private TourImageStatus tourImageStatus = TourImageStatus.ACTIVE;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;
}