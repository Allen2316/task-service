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
@RequestMapping(value = "/api/v1/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll() {
        List<TaskDto> tasks = this.taskService.getAll();
        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tasks);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.taskService.getById(id));
    }

    @PostMapping(headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<TaskDto> create(@RequestBody @Validated TaskDto taskDto) {
        log.info("Creado la taskDto = " + taskDto);
        return new ResponseEntity<>(this.taskService.create(taskDto), HttpStatus.CREATED);
    }

    @PutMapping(headers = "Accept=application/json;charset=UTF-8", value = "/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable Long id, TaskDto taskDto) {
        return new ResponseEntity<>(this.taskService.updateById(id, taskDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(this.taskService.deleteById(id));
    }

}
