package com.allen.backend.controller;

import com.allen.backend.domain.dto.TaskDto;
import com.allen.backend.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll() {
        return ResponseEntity.ok(this.taskService.getAll());
    }

    @PostMapping(headers = "Accept=application/json;charset=UTF-8", value = "/register")
    public ResponseEntity<TaskDto> create(@RequestBody @Validated TaskDto taskDto) {
        log.info("Creado la taskDto = "+taskDto);
        return new ResponseEntity<>(this.taskService.create(taskDto), HttpStatus.CREATED);
    }
}
