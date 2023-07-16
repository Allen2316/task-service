package com.allen.backend.domain.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class CategoryDto {
    private Long id;
    @NotEmpty(message = "Nombre de categoria no puede ser null ni vacío")
    private String name;
}
