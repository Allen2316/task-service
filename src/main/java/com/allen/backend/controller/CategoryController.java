package com.allen.backend.controller;

import com.allen.backend.domain.dto.CategoryDto;
import com.allen.backend.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api/v1/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.categoryService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(this.categoryService.getAll());
    }

    @PostMapping(headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(this.categoryService.create(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping(headers = "Accept=application/json;charset=UTF-8", value = "/{id}")
    public ResponseEntity<CategoryDto> updateById(@PathVariable long id, CategoryDto categoryDto) {
        return new ResponseEntity<>(this.categoryService.updateById(id, categoryDto), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteById(long id) {
        return ResponseEntity.ok(this.categoryService.deleteById(id));
    }
}
