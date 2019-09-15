package main.service.impl;

import main.model.Task;
import main.repository.TaskRepository;
import main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService
{
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task getTask(int id)
    {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    @Override
    public Task addTask(Task task)
    {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(int id)
    {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> listTask()
    {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        taskIterable.forEach(tasks::add);
        return tasks;
    }
}
