package main.service;
import main.model.Task;
import java.util.List;

public interface TaskService
{
    Task getTask(int id);
    Task addTask(Task task);
    void deleteTask(int id);
    List<Task> listTask();
}
