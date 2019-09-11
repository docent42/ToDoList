package main;

import response.Task;

import java.util.ArrayList;

class TaskStorage
{
    private static ArrayList<Task> tasks = new ArrayList<>();

    static ArrayList<Task> getAllTasks() {
        return tasks;
    }

    static int addTask(Task task)
    {
        int id = tasks.size() +1;
        task.setId(id);
        tasks.add(task);
        return id;
    }
}
