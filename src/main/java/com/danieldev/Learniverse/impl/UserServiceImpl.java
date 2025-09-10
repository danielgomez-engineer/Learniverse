package com.danieldev.Learniverse.impl;

import com.danieldev.Learniverse.dto.request.UserRequest;
import com.danieldev.Learniverse.dto.response.UserResponse;
import com.danieldev.Learniverse.exception.ResourceNotFoundException;
import com.danieldev.Learniverse.model.Rol;
import com.danieldev.Learniverse.model.User;
import com.danieldev.Learniverse.repository.UserRepository;
import com.danieldev.Learniverse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponse create(UserRequest request) {
        User user = mapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRol(Rol.CLIENTE);
        User saved = userRepository.save(user);
        return mapper.map(saved, UserResponse.class);
    }

    @Override
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("usuario no encontrado. impl/UserServiceImpl/findbyid"));
        return mapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(user -> mapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {

        //pendiente
        return null;
    }


    @Override
    public void delete(Long id) {
            if(!userRepository.existsById(id)) {
                throw new ResourceNotFoundException("NO hay usuario registrado con ese id, "
                 + " impl/UserService/Impl/delete.");
            }
    }
}
