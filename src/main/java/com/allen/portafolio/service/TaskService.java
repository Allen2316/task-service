package com.allen.portafolio.service;

import java.util.List;

import com.allen.portafolio.domain.Task;

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
    Task getById(long id);

    /**
     * Obtener todas las Tareas, ideal para un administrador
     * 
     * @return Lista de Tareas
     */
    List<Task> getAll();

    /**
     * Obtener todas las Tareas por usuario mediante su ID
     * 
     * @param id ID del usuario a obtener la lista de Tareas
     * @return La lista de Tareas del usuario especificado
     */
    List<Task> getAllByUserId(long id);

    /**
     * Crear una nueva Tarea
     * @param task Objeto que contiene la información de la Tarea
     * @return La Tarea creada
     */
    Task create(Task task);

    /**
     * Actualizar Tarea mediante Id
     * @param id ID del registro a actualizar
     * @param task Objeto que contiene la información de la Tarea
     * @return La Tarea actualizada
     */
    Task update(long id, Task task);


    /**
     * Eliminar Tarea por ID
     * @param id ID del registro a eliminar
     * @return ID del registro  que se eliminó
     */
    Long deleteById(long id);

}
