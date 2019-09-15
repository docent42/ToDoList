package main;

import main.model.Task;
import main.repository.TaskRepository;
import java.util.ArrayList;
import java.util.Optional;


class TaskStorage
{

    static ArrayList<Task> getAllTasks(TaskRepository taskRepository)
    {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        taskIterable.forEach(tasks::add);
        return tasks;
    }

    static int addTask(Task task,TaskRepository taskRepository)
    {
         Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

    static Task getTask(int taskId,TaskRepository taskRepository)
    {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        return optionalTask.orElse(null);
    }

    static void removeTask(int taskId,TaskRepository taskRepository)
    {
        taskRepository.deleteById(taskId);
    }
}
