package com.YogApp.app.service;

import com.YogApp.app.Exception.NotFoundException;
import com.YogApp.app.model.entites.User;
import com.YogApp.app.model.enums.Role;
import com.YogApp.app.model.request.UserReq;
import com.YogApp.app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSvc {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    @Qualifier("BCript")
    private PasswordEncoder encoder;

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User findUserById(int id) throws NotFoundException {
        return userRepo.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User saveUser(UserReq userReq){
        User user = new User();
        user.setName(userReq.getName());
        user.setSurname(userReq.getSurname());
        user.setUsername(userReq.getUsername());
        user.setEmail(userReq.getEmail());
        user.setRole(Role.ADMIN);
        user.setPassword(encoder.encode(userReq.getPassword()));
        return userRepo.save(user);
    }

    public User upDateUser(int id, UserReq userReq) throws NotFoundException {
        User user = findUserById(id);
        user.setName(userReq.getName());
        user.setSurname(userReq.getSurname());
        user.setUsername(userReq.getUsername());
        user.setEmail(userReq.getEmail());
        user.setPassword(encoder.encode(userReq.getPassword()));
        return userRepo.save(user);
    }

    public void deleteUser(int id) throws NotFoundException {
        User user = findUserById(id);
        userRepo.delete(user);
    }

    public User findUserByUsername(String username) throws NotFoundException{
        return userRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("Username/Password do not match"));
    }
}
