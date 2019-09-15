package main.controller;

import main.repository.TaskRepository;
import main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;
import java.util.List;

@RestController
public class TaskController
{
    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/tasks/")
    public List<Task> list()
    {
        return taskService.listTask();
    }

    @PostMapping(value = "/tasks/")
    public int add(Task task)
    {
        return taskService.addTask(task).getId();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id)
    {
        Task task = taskService.getTask(id);
        if (task == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else
            return  new ResponseEntity(task,HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity delete(@PathVariable int id)
    {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
