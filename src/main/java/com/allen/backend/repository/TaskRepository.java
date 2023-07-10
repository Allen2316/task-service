package com.allen.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allen.backend.domain.Task;

@Repository //esto es opcional porque ya hereda de JPA
public interface TaskRepository extends JpaRepository<Task, Long> {
}
