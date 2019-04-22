package com.rlocke.redisSpringApp;


import com.rlocke.redisSpringApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
     UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User addUser(@RequestBody User user){
        userRepository.save(user);
        return user;
    }

    /*
    @RequestMapping(value = "/addUser}", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User addUser(@RequestBody User user) {
        userRepository.save(user);
        return userRepository.findById(user.getId());
    }*/



    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User updateUser(@PathVariable("id") int id, @RequestBody User user){

        User thisUser =  userRepository.findById(id);
        thisUser.setName(user.getName());
        thisUser.setSalary(user.getSalary());

        userRepository.save(thisUser);
        return thisUser;

    }



    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<Integer, User> delete(@PathVariable("id") final Integer id) {
        userRepository.delete(id);
        return null;
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<User>> all() {
        Map<String, User> map = userRepository.findAll();
        ArrayList<User> list = new ArrayList<>();
        for(User user: map.values()){
            list.add(user);
        }
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User viewUser(@PathVariable int id) {
        User user = userRepository.findById(id);
        return user;
    }

}

