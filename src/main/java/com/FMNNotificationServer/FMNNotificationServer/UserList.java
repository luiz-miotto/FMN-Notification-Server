package com.FMNNotificationServer.FMNNotificationServer;

import com.FMNNotificationServer.FMNNotificationServer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserList {

    @Autowired
    UserRepository userRepository;

    UserList(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void printUserNames(){
        for(User user: userRepository.findAll()){
            System.out.println(user.getName());
        }
    }

    @GetMapping("/getUserList")
    public List<User> getUserList(){
        return (List<User>) userRepository.findAll();
    }
}