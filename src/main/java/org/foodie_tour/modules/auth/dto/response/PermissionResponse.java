package org.foodie_tour.modules.auth.dto.response;
import lombok.*;
import org.foodie_tour.modules.auth.enums.PermissionStatus;

@AllArgsConstructor
@Data
@ToString
@NoArgsConstructor
public class PermissionResponse {
    private Long permissionId;
    private String name;
    private String description;
    // private PermissionType type;
    private PermissionStatus status;
}
