package com.FinC.services;

import java.util.List;
import java.util.UUID;

import com.FinC.dtos.AuthDto;
import com.FinC.dtos.UserRequestDto;
import com.FinC.dtos.UserResponseDto;
import com.FinC.infra.security.TokenService;
import com.FinC.models.User;
import com.FinC.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @Autowired
    EmailService emailService;

    public UserResponseDto findById(UUID id) {
        var user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
        return new UserResponseDto(user.getEmail(),user.getName(), user.getUserId());
    }
    public List<UserResponseDto> findAll(){
        var users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponseDto(user.getName(), user.getEmail(),user.getUserId()))
                .toList();
    }
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if(userRepository.findByEmail(userRequestDto.email()) != null) return null;
        String encryptedPass = new BCryptPasswordEncoder().encode(userRequestDto.password());

        var user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        user.setPassword(encryptedPass);
        userRepository.save(user);
        return new UserResponseDto(user.getEmail(),user.getName(),user.getUserId());
    }
    public String returnToken(AuthDto authDto) {
        var usernamePassord = new UsernamePasswordAuthenticationToken(authDto.email(), authDto.password());
        var auth = this.authenticationManager.authenticate(usernamePassord);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return token;
    }

    public UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto) {
        var user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
        BeanUtils.copyProperties(userRequestDto, user);
        userRepository.save(user);
        return new UserResponseDto(user.getEmail(),user.getName(), user.getUserId());

    }
    public void deleteUser(UUID id) {
        var user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
        userRepository.delete(user);
    }
}