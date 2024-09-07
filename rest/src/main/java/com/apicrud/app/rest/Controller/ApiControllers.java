package com.apicrud.app.rest.Controller;

import com.apicrud.app.rest.Models.User;
import com.apicrud.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// SWAGGER DOCUMENTATION
// https://app.swaggerhub.com/apis-docs/VitaliPoluboc/CRUDRestAPI/1.0.0

@RestController
public class ApiControllers {

    @Autowired
    private UserRepo userRepo;


    @GetMapping(value = "/user/{id}")
    public User getPage(@PathVariable long id) {
        User user = userRepo.findById(id).get();
        return user;
    }

    @GetMapping(value="/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping(value = "/create")
    public String createUser(@RequestBody User user) {
        userRepo.save(user);
        return "User created";
    }

    @PutMapping(value = "/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User updatedUser = userRepo.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setOccupation(user.getOccupation());
        updatedUser.setAge(user.getAge());
        userRepo.save(updatedUser);
        return "User updated";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        User deletedUser = userRepo.findById(id).get();
        userRepo.delete(deletedUser);
        return "User deleted";
    }

}
