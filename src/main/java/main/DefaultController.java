package main;

import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class DefaultController
{
    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping("/")
    public String index(Model model)
    {
        model.addAttribute("tasks",TaskStorage.getAllTasks(taskRepository));
        return "index";
    }
}
