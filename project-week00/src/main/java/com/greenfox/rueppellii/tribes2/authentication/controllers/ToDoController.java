package com.greenfox.rueppellii.tribes2.authentication.controllers;

import com.greenfox.rueppellii.tribes2.authentication.models.ApplicationUser;
import com.greenfox.rueppellii.tribes2.authentication.models.ToDo;
import com.greenfox.rueppellii.tribes2.authentication.models.ToDoDTO;
import com.greenfox.rueppellii.tribes2.authentication.models.UserDTO;
import com.greenfox.rueppellii.tribes2.authentication.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {

    private ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/")
    public Iterable<ApplicationUser> listUsers() {
        return toDoService.listUsers();
    }

//    @PostMapping("/register")
//    public String giveMeUser(@RequestBody UserDTO userObject) {
//        ApplicationUser applicationUser = new ApplicationUser();
//        applicationUser.setUsername(userObject.getUsername());
//        applicationUser.setPassword(userObject.getPassword());
//        toDoService.saveUser(applicationUser);
//        return "redirect:/";
//    }

    @PostMapping("/{userId}/addtodo")
    public String addToDo(@RequestBody ToDoDTO toDoObject, @PathVariable Long userId) {
        ToDo toDo = new ToDo();
        toDo.setTitle(toDoObject.getTitle());
        toDo.setDetail(toDoObject.getDetail());
        toDoService.addToDo(userId, toDo);
        return "redirect:/" + userId + "/todos";
    }

    @GetMapping("/{userId}/todos")
    public List<ToDo> listToDos(@PathVariable Long userId) {
        return toDoService.listToDos(userId);
    }
}
