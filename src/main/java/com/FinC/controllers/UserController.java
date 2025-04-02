package com.FinC.controllers;

import java.util.List;
import java.util.UUID;

import com.FinC.dtos.AuthDto;
import com.FinC.dtos.UserRequestDto;
import com.FinC.dtos.UserResponseDto;
import com.FinC.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;


    @Operation(description="Lista todos os usuarios")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @Operation(description="Lista usuario pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable(name = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @Operation(description="Registra um usuario")
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequestDto));
    }

    @Operation(description="Login de usuario")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthDto authDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.returnToken(authDto));
    }

    @Operation(description="Atualiza um usuario")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable(name = "id")UUID id,
                                                      @RequestBody UserRequestDto userRequesDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(id, userRequesDto));
    }


    @Operation(description="Deleta um usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable(name = "id")UUID id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}