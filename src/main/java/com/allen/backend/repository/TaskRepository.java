package com.allen.backend.repository;

import com.allen.backend.domain.Category;
import com.allen.backend.domain.dto.TaskDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allen.backend.domain.Task;

import java.util.List;

@Repository //esto es opcional porque ya hereda de JPA
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCategory(Category category);
}
