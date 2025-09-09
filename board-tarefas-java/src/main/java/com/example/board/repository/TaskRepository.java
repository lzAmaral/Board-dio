package com.example.board.repository;

import com.example.board.domain.Task;
import com.example.board.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatusOrderByUpdatedAtDesc(TaskStatus status);
}
