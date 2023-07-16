package com.allen.backend.service;

import com.allen.backend.domain.Category;
import com.allen.backend.domain.Task;

import com.allen.backend.domain.dto.TaskDto;
import com.allen.backend.repository.TaskRepository;
import com.allen.backend.service.impl.TaskServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class TaskServiceMockTest {
    private TaskRepository taskRepositoryMock;
    private TaskService taskService;

    //mock sirve para pruebas unitarias sin levantar el contexto de springboot ni accediendo a la bd, si no solo mocks

    @BeforeEach
    public void setup() {
        //Se mockea el repository
        this.taskRepositoryMock = Mockito.mock(TaskRepository.class);
        this.taskService = new TaskServiceImpl(this.taskRepositoryMock);

        Task taskEntity = Task.builder()
                .id(1L)
                .category(Category.builder().id(1L).build())
                .completed(false)
                .description("Es la descripcion")
                .name("Task1")
                .userId(1L)
                .build();

        Mockito.when(taskRepositoryMock.findById(1L)).thenReturn(Optional.of(taskEntity));
        Mockito.when(taskRepositoryMock.save(taskEntity)).thenReturn(taskEntity);
    }

    @Test
    public void whenValidGetId_ThenReturnTask() {
        TaskDto found = taskService.getById(1L);
        Assertions.assertThat(found.getName()).isEqualTo("Task1");
    }

    @Test
    public void whenValidUpdateTask_ThenReturnNewTask(){
        TaskDto taskDto = TaskDto.builder()
                .id(1L)
                .category(Category.builder().id(1L).build())
                .completed(false)
                .description("Es la descripcion editada")
                .name("Task1 Updated")
                .userId(1L)
                .build();
        log.info("TaskDto " + taskDto);

        TaskDto newTask = taskService.updateById(taskDto.getId(), taskDto);

        Assertions.assertThat(newTask.getId()).isEqualTo(1L); // El ID no cambia
        Assertions.assertThat(newTask.getName()).isEqualTo("Task1 Updated");
        Assertions.assertThat(newTask.getDescription()).isEqualTo("Es la descripcion editada");
        Assertions.assertThat(newTask.isCompleted()).isFalse();
    }
}
