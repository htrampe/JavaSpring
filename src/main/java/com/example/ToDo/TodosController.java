package com.example.ToDo;

import java.util.ArrayList;

import com.example.ToDo.models.Todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TodosController {
    
    ArrayList<Todo> todos;

    public TodosController(){
        setTodos(new ArrayList<Todo>());

        createDemoData();
    }

    private void createDemoData(){
        getTodos().add(new Todo("Müll rausbringen!", "Amelie"));
        getTodos().add(new Todo("Küche aufräumen", "Jordan"));
    }

    // Personen erstellen und zurückgeben
    private ArrayList<String> getPersonen(){
        ArrayList<String> personen = new ArrayList<>();

        personen.add("Amelie");
        personen.add("Alex");
        personen.add("Theo");
        personen.add("Bent");
        personen.add("Jordan");
        personen.add("Lennard");
        personen.add("Nils");
        personen.add("Holger");

        return personen;
    }

    @GetMapping("/todos")
    public String todos(@RequestParam(name="activePage", required = false, defaultValue = "todos") String activePage, Model model){
        model.addAttribute("activePage", "todos");
        model.addAttribute("todos", getTodos());

        // Personen für eine ToDo holen
        model.addAttribute("personen", getPersonen());

        return "index.html";
    }

    @RequestMapping("/deltodo")
    public String deltodo(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "todos") String activePage, Model model){
        getTodos().remove(id);
        return "redirect:/todos";
    }

    @RequestMapping("/changetodo")
    public String changetodo(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "changetodo") String activePage, Model model){
        // Todo zur Bearbeitung laden
        model.addAttribute("todo", getTodos().get(id));
        model.addAttribute("todoid", id);

        // Mögliche Personen hier hinzufügen
        model.addAttribute("personen", getPersonen());
        
        model.addAttribute("activePage", "todoUpdate");
        return "index.html";
    }
    
    @RequestMapping("/updatetodo")
    public String updatetodo(@RequestParam(name="todoId", required = true, defaultValue = "null") int todoId, @RequestParam(name="todoDesc", required = true, defaultValue = "null") String todoDesc,@RequestParam(name="todoPerson", required = true, defaultValue = "null") String todoPerson, @RequestParam(name="activePage", required = false, defaultValue = "todos") String activePage, Model model){
        getTodos().get(todoId).setDesc(todoDesc);
        getTodos().get(todoId).setPerson(todoPerson);
        return "redirect:/todos";
    }
    
    @RequestMapping("/addtodo")
    public String addtodo(@RequestParam(name="todoDesc", required = true, defaultValue = "null") String todoDesc,@RequestParam(name="todoPerson", required = true, defaultValue = "null") String todoPerson, @RequestParam(name="activePage", required = false, defaultValue = "todos") String activePage, Model model){
        getTodos().add(new Todo(todoDesc, todoPerson));
        return "redirect:/todos";
    }
    
    public void setTodos(ArrayList<Todo> todos) {
        this.todos = todos;
    }

    public ArrayList<Todo> getTodos() {
        return todos;
    }
}
