package com.allen.backend.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TaskDto {
    private Long id;

    @NotEmpty(message = "El nombre de la tarea no puede ser nulo o vacío")
    private String name;

    private String description;
    private boolean completed;

}
