package com.allen.backend;

import com.allen.backend.domain.Category;
import com.allen.backend.domain.Task;
import com.allen.backend.domain.dto.TaskDto;
import com.allen.backend.repository.TaskRepository;
import com.allen.backend.service.TaskService;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class TaskRepositoryMockTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void whenFindByCategory_thenReturnListTask(){
        Task task01 = Task.builder()
                .name("Estudios")
                .description("Es la descripcion")
                .completed(false)
                .userId(1L)
                .category(Category.builder().id(1L).build())
                .build();

        taskRepository.save(task01);

        List<Task> founds = taskRepository.findByCategory(task01.getCategory());

        Assertions.assertThat(founds.size()).isEqualTo(3);
    }
}
