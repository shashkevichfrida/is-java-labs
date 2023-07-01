package org.example.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.dto.UserDto;
import org.example.services.impl.UserServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "user", description = "user APIs")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @PreAuthorize("hasAuthority('developers:write')")
    @PostMapping("/save")
    public UserDto save(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }
    @PostMapping("/getById")
    public UserDto getById(@RequestParam Long id){
        return userService.getById(id);
    }

    @PostMapping("/deleteById")
    public void deleteById(@RequestParam Long id){
        userService.deleteById(id);
    }
    @PostMapping("/deleteByEntity")
    public void deleteByEntity(@RequestParam Long id){
        userService.deleteByEntity(id);
    }
    @PostMapping("/deleteAll")
    public void deleteAll(){
        userService.deleteAll();
    }
    @PostMapping("/update")
    public void update(@RequestBody UserDto userDto, @RequestParam Long id){
        userService.update(userDto, id);
    }
    @PostMapping("/getAll")
    public List<UserDto> getAll(){
        return userService.getAll();
    }

}
