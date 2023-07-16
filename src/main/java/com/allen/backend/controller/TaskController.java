package com.allen.backend.controller;

import com.allen.backend.controller.exceptions.ErrorMessage;
import com.allen.backend.domain.Category;
import com.allen.backend.domain.Task;
import com.allen.backend.domain.dto.TaskDto;
import com.allen.backend.exceptions.CustomNotFoundException;
import com.allen.backend.service.TaskService;
import com.allen.backend.util.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api/v1/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll(@RequestParam(name = "categoryId", required = false) Long categoryId) {
        List<TaskDto> tasks;
        if (categoryId == null) {
            tasks = this.taskService.getAll();
            if (tasks.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        } else {
            tasks = this.taskService.findByCategory(Category.builder().id(categoryId).build());
            if (tasks.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(tasks);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable Long id) {
        try {
            TaskDto taskDto = this.taskService.getById(id);
            return ResponseEntity.ok(taskDto);
        } catch (CustomNotFoundException ex) {
            log.info("Error ==== ", ex);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<TaskDto> create(@RequestBody @Valid TaskDto taskDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessage.formatMessage(result));
        }
        return new ResponseEntity<>(this.taskService.create(taskDto), HttpStatus.CREATED);
    }

    @PutMapping(headers = "Accept=application/json;charset=UTF-8", value = "/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable Long id, @RequestBody @Valid TaskDto taskDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessage.formatMessage(result));
        }
        return new ResponseEntity<>(this.taskService.updateById(id, taskDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.taskService.deleteById(id));
        } catch (CustomNotFoundException ex) {
            log.info("Error ==== ", ex);
            return ResponseEntity.notFound().build();
        }
    }

}
