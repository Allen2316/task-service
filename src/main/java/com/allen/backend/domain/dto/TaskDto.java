package com.allen.backend.domain.dto;

import com.allen.backend.domain.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class TaskDto {
    private Long id;

    @NotEmpty(message = "El nombre de la tarea no puede ser nulo o vacío")
    private String name;

    private String description;
    private boolean completed;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;


}
