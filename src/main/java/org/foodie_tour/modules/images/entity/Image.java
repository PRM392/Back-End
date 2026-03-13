package org.foodie_tour.modules.images.entity;

import jakarta.persistence.*;
import lombok.*;
import org.foodie_tour.modules.feedback.entity.Feedback;
import org.foodie_tour.modules.images.enums.ImageStatus;
import org.foodie_tour.modules.tours.entity.Dish;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "images")
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private Long imageId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_description")
    private String imageDescription;

    @Builder.Default
    @Column(name = "image_status")
    @Enumerated(EnumType.STRING)
    private ImageStatus imageStatus = ImageStatus.ACTIVE;

    @Builder.Default
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CheckPointImage> checkPointImages;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TourImage> tourImages;
}