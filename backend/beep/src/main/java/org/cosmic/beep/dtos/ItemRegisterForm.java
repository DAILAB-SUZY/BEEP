package org.cosmic.beep.dtos;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for {@link org.cosmic.beep.entities.Item}
 */
public record ItemRegisterForm(
    @NotBlank(message = "Name is mandatory")
    String name,
    String description,
    String thumbnail,
    @NotBlank(message = "Category is mandatory")
    String category,
    @NotBlank(message = "Location is mandatory")
    String location) {

}