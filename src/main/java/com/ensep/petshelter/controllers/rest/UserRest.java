package com.ensep.petshelter.controllers.rest;

import com.ensep.petshelter.dto.user.UserEntityDTO;
import com.ensep.petshelter.dto.user.UserEntityUpdateDTO;
import com.ensep.petshelter.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserRest {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserEntityDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntityDTO> updateUser(@PathVariable Long id, @RequestBody UserEntityUpdateDTO userUpdate){
        return ResponseEntity.ok(userService.updateUser(id, userUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.removeUser(id);
        return ResponseEntity.noContent().build();
    }
}
