package main.controller;

import main.model.Task;
import main.repository.TaskRepository;
import main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


@Controller
public class DefaultController
{
    @Autowired
    private TaskService taskService;

    @RequestMapping("/")
    public String index(Model model)
    {
        model.addAttribute("tasks",taskService.listTask());
        return "index";
    }
}
