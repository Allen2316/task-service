package com.allen.backend.service.impl;

import java.util.List;
import java.util.Optional;

import com.allen.backend.domain.dto.TaskDto;
import com.allen.backend.exceptions.CustomNotFoundException;
import com.allen.backend.util.Mapper;
import org.springframework.stereotype.Service;

import com.allen.backend.domain.Task;
import com.allen.backend.repository.TaskRepository;
import com.allen.backend.service.TaskService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor //esta etiqueta es para crear inyeccion por constructor
public class TaskServiceImpl implements TaskService {

    //Inyectamos dependencias
    private final TaskRepository taskRepository;

    @Override
    public TaskDto getById(long id) {
        Task task = getEntityById(id);
        return converterEntityToDto(task);
    }

    @Override
    public List<TaskDto> getAll() {
        List<Task> tasks = this.taskRepository.findAll();
        //return tasks.stream().map(element -> converterEntityToDto(element)).toList();
        return tasks.stream().map(this::converterEntityToDto).toList();
    }

    @Override
    public TaskDto create(TaskDto taskDto) {
        Task task = converterDtoToEntity(taskDto);
        return converterEntityToDto(this.taskRepository.save(task));
    }

    @Override
    public TaskDto update(long id, TaskDto taskDto) {
        Task task = getEntityById(id);
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setCompleted(taskDto.isCompleted());
        return converterEntityToDto(this.taskRepository.save(task));
    }

    @Override
    public Long deleteById(long id) {
        getEntityById(id);
        this.taskRepository.deleteById(id);
        return id;
    }


    private Task getEntityById(Long id) {
        Optional<Task> optional = this.taskRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomNotFoundException("No se encontró el registro con id " + id);
        }
        return optional.get();
    }

    private TaskDto converterEntityToDto(Task entity) {
        return Mapper.modelMapper().map(entity, TaskDto.class);
    }

    private Task converterDtoToEntity(TaskDto taskDto) {
        return Mapper.modelMapper().map(taskDto, Task.class);
    }

}
