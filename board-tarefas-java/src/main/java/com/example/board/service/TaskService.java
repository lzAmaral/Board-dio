package com.example.board.service;

import com.example.board.domain.Task;
import com.example.board.domain.TaskStatus;
import com.example.board.domain.User;
import com.example.board.dto.TaskDTO;
import com.example.board.repository.TaskRepository;
import com.example.board.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Map<TaskStatus, List<Task>> getBoard() {
        Map<TaskStatus, List<Task>> map = new EnumMap<>(TaskStatus.class);
        for (TaskStatus st : TaskStatus.values()) {
            map.put(st, taskRepository.findByStatusOrderByUpdatedAtDesc(st));
        }
        return map;
    }

    @Transactional
    public Task create(TaskDTO dto) {
        Task t = new Task();
        t.setTitle(dto.getTitle());
        t.setDescription(dto.getDescription());
        t.setStatus(dto.getStatus());
        if (dto.getAssignedUserId() != null) {
            User u = userRepository.findById(dto.getAssignedUserId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
            t.setAssignedTo(u);
        }
        return taskRepository.save(t);
    }

    @Transactional
    public Task move(Long id, TaskStatus newStatus) {
        Task t = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
        t.setStatus(newStatus);
        return taskRepository.save(t);
    }

    @Transactional
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
