package com.allen.backend.service.impl;

import com.allen.backend.domain.Category;
import com.allen.backend.domain.dto.CategoryDto;
import com.allen.backend.exceptions.CustomNotFoundException;
import com.allen.backend.repository.CategoryRepository;
import com.allen.backend.service.CategoryService;
import com.allen.backend.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor //esta etiqueta es para crear inyeccion por constructor
public class CategoryImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto getById(long id) {
        Category category = getEntityById(id);
        return converterEntityToDto(category);
    }


    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories = this.categoryRepository.findAll();
        return categories.stream().map(this::converterEntityToDto).toList();
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = converterDtoToEntity(categoryDto);
        return converterEntityToDto(this.categoryRepository.save(category));
    }

    @Override
    public CategoryDto updateById(long id, CategoryDto categoryDto) {
        Category category = getEntityById(id);
        category.setName(categoryDto.getName());
        return converterEntityToDto(this.categoryRepository.save(category));
    }

    @Override
    public Long deleteById(long id) {
        getEntityById(id);
        this.categoryRepository.deleteById(id);
        return id;
    }

    private Category getEntityById(long id) {
        Optional<Category> optional = this.categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomNotFoundException("No se encontró el registro con id: " + id);
        }
        return optional.get();
    }

    private CategoryDto converterEntityToDto(Category entity) {
        return Mapper.modelMapper().map(entity, CategoryDto.class);
    }

    private Category converterDtoToEntity(CategoryDto categoryDto) {
        return Mapper.modelMapper().map(categoryDto, Category.class);
    }


}
