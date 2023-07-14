package com.allen.backend.service;

import java.util.List;

import com.allen.backend.domain.Category;
import com.allen.backend.domain.dto.TaskDto;

/**
 * Gestionar CRUD de Tareas
 *
 * @author Allen
 * @version 10/07/2023
 */

public interface TaskService {
    /**
     * Obtener una Tarea por su ID
     *
     * @param id ID de la Tarea a obtener
     * @return La Tarea encontrada
     */
    TaskDto getById(long id);

    /**
     * Obtener todas las Tareas
     *
     * @return Lista de Tareas
     */
    List<TaskDto> getAll();

    /**
     * Crear una nueva Tarea
     *
     * @param taskDto Objeto que contiene la información de la Tarea
     * @return La Tarea creada
     */
    TaskDto create(TaskDto taskDto);

    /**
     * Actualizar Tarea mediante Id
     *
     * @param id   ID del registro a actualizar
     * @param taskDto Objeto que contiene la información de la Tarea
     * @return La Tarea actualizada
     */
    TaskDto updateById(long id, TaskDto taskDto);


    /**
     * Eliminar Tarea por ID
     *
     * @param id ID del registro a eliminar
     * @return ID del registro  que se eliminó
     */
    Long deleteById(long id);

    /**
     * Obtener las tareas por categoria
     * @param category Objeto que contiene la informacion de categoria a buscar
     * @return Lista de tareas con la misma categoria
     */
    List<TaskDto> findByCategory(Category category);

}
