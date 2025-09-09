package com.example.board.web;

import com.example.board.domain.TaskStatus;
import com.example.board.dto.TaskDTO;
import com.example.board.service.TaskService;
import com.example.board.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class BoardController {

    private final TaskService taskService;
    private final UserRepository userRepository;

    public BoardController(TaskService taskService, UserRepository userRepository) {
        this.taskService = taskService;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Model model, @ModelAttribute("task") TaskDTO task) {
        model.addAttribute("board", taskService.getBoard());
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @PostMapping("/tasks")
    public String create(@Valid @ModelAttribute("task") TaskDTO dto,
                         BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("board", taskService.getBoard());
            model.addAttribute("statuses", TaskStatus.values());
            model.addAttribute("users", userRepository.findAll());
            return "index";
        }
        taskService.create(dto);
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/move")
    public String move(@PathVariable Long id, @RequestParam("status") TaskStatus status) {
        taskService.move(id, status);
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/delete")
    public String delete(@PathVariable Long id) {
        taskService.delete(id);
        return "redirect:/";
    }
}
