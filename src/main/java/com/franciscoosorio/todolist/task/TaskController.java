package com.franciscoosorio.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franciscoosorio.todolist.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity registerTask(@RequestBody TaskModel task, HttpServletRequest request){

        UUID idUser = (UUID) request.getAttribute("idUser");
        task.setIdUser(idUser);

        var current_date = LocalDateTime.now();

        if (current_date.isAfter(task.getStartAt()) || current_date.isAfter(task.getEndAt()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The start date or end date must be greater than the current date!");

        if (task.getStartAt().isAfter(task.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The end date must be greater than the start date!");
        }
            
        
        var taskRegistered = this.taskRepository.save(task);

        return ResponseEntity.status(HttpStatus.OK).body(taskRegistered);
    }

    @GetMapping("/")
    public List<TaskModel> listTasks(HttpServletRequest request){
        
        var idUser = request.getAttribute("idUser");
        return this.taskRepository.findByIdUser((UUID) idUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTask(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request){

        var task = this.taskRepository.findById(id).orElse(null);

        if (task == null) {
            return ResponseEntity.badRequest().body("Task not found!");
        }

        var idUser = request.getAttribute("idUser");

        if (!task.getIdUser().equals(idUser)) {
            return ResponseEntity.badRequest().body("User does not have permissions!");
        }

        Utils.copyNonNullProperties(taskModel, task);

        var task_updated = this.taskRepository.save(task);

        return ResponseEntity.ok().body(task_updated);
    }
}
