package com.example.ToDo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodosController {
    
    ArrayList<String> todos;

    public TodosController(){
        setTodos(new ArrayList<String>());

        getTodos().add("Müll rausbringen");
        getTodos().add("Küche aufräumen");
    }

    @GetMapping("/todos")
    public String todos(@RequestParam(name="activePage", required = false, defaultValue = "todos") String activePage, Model model){
        model.addAttribute("activePage", "todos");
        model.addAttribute("todos", getTodos());
        return "index.html";
    }

    @RequestMapping("/deltodo")
    public String deltodo(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "todos") String activePage, Model model){
        
        getTodos().remove(id);

        model.addAttribute("todos", getTodos());
        model.addAttribute("activePage", "todos");
        return "index.html";
    }

    @RequestMapping("/changetodo")
    public String changetodo(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "changetodo") String activePage, Model model){
        
        // Todo zur Bearbeitung laden
        model.addAttribute("todo", getTodos().get(id));
        model.addAttribute("todoid", id);
        model.addAttribute("activePage", "todoUpdate");
        return "index.html";
    }

    @RequestMapping("/updatetodo")
    public String updatetodo(@RequestParam(name="todoId", required = true, defaultValue = "null") String todoId, @RequestParam(name="todoDesc", required = true, defaultValue = "null") String todoDesc, @RequestParam(name="activePage", required = false, defaultValue = "todos") String activePage, Model model){
        getTodos().set(Integer.parseInt(todoId), todoDesc);
        return "redirect:/todos";
    }

    @RequestMapping("/addtodo")
    public String addtodo(@RequestParam(name="todoDesc", required = true, defaultValue = "null") String todoDesc, @RequestParam(name="activePage", required = false, defaultValue = "todos") String activePage, Model model){
        
        getTodos().add(todoDesc);
        
        model.addAttribute("todos", getTodos());
        model.addAttribute("activePage", "todos");
        return "index.html";
    }

    public void setTodos(ArrayList<String> todos) {
        this.todos = todos;
    }

    public ArrayList<String> getTodos() {
        return todos;
    }
}
