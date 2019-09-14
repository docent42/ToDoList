package main;

import main.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class TaskStorage
{
    private static int currentId = 1;
    private static HashMap<Integer,Task> tasks = new HashMap<>();

    static List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    static int addTask(Task task)
    {
        int id = currentId++;
        task.setId(id);
        tasks.put(id,task);
        return id;
    }

    static Task getTask(int taskId)
    {
        return tasks.getOrDefault(taskId, null);
    }

    public static void removeTask(int taskId)
    {
        tasks.remove(taskId);
    }
}
