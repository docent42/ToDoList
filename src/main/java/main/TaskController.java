package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import java.util.List;

@RestController
public class TaskController
{
    @GetMapping(value = "/tasks/")
    public List<Task> list()
    {
        return TaskStorage.getAllTasks();
    }

    @PostMapping(value = "/tasks/")
    public int add(Task task)
    {
       return TaskStorage.addTask(task);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id)
    {
        Task task = TaskStorage.getTask(id);
        if (task == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return new ResponseEntity(task,HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity delete(@PathVariable int id)
    {
        TaskStorage.removeTask(id);
        return  ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
