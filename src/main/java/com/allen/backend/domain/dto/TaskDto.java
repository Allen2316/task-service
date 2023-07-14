package com.allen.backend.domain.dto;

import com.allen.backend.domain.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskDto {
    private Long id;

    @NotEmpty(message = "El nombre de la tarea no puede ser nulo o vacío")
    private String name;

    private String description;
    private boolean completed;
    private Category category;
    @NotNull(message = "El userID de la tarea no puede ser nulo o vacío")
    private Long userId;

}
