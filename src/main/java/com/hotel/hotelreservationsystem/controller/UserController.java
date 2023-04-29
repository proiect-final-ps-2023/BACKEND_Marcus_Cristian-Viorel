package com.hotel.hotelreservationsystem.controller;

import com.hotel.hotelreservationsystem.dto.UserDTO;
import com.hotel.hotelreservationsystem.model.Room;
import com.hotel.hotelreservationsystem.model.RoomType;
import com.hotel.hotelreservationsystem.model.User;
import com.hotel.hotelreservationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public List<UserDTO> findAll() {
        return userService.getAllUsers().stream()
                .map(User::mapToDTO)
                .toList();
    }

    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        User user = userService.deleteUser(userService.findUserById(id));

        if(user == null) {
            throw new IllegalArgumentException("User with id " + id + " does not exist");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody User user) {
        try {
            User foundUser = userService.login(user.getName(), user.getPass());
            if(foundUser == null) {
                throw new Exception("Invalid credentials");
            }

            UserDTO foundUserDTO = foundUser.mapToDTO();
            if(!(foundUserDTO.getName().equals(user.getName()) && foundUserDTO.getPass().equals(user.getPass()))) {
                throw new Exception("Invalid credentials");
            }
            return ResponseEntity.status(HttpStatus.OK).body(foundUserDTO);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @PutMapping("/update/{id}/{name}/{pass}/{isAdmin}")
    public void update(@PathVariable Long id, @PathVariable String name, @PathVariable String pass, @PathVariable Boolean isAdmin) {
        User user = userService.getAllUsers().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);

        user.setName(name);
        user.setPass(pass);
        user.setIsAdmin(isAdmin);

        userService.updateUser(user);
    }

    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @PutMapping("/add")
    public void add(@RequestBody User user) {
        userService.createUser(user);
    }
}
