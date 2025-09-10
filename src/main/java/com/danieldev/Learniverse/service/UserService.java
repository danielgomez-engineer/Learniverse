package com.danieldev.Learniverse.service;

import com.danieldev.Learniverse.dto.request.UserRequest;
import com.danieldev.Learniverse.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create (UserRequest request);

    UserResponse findById(Long id);

    List<UserResponse> findAll();

    UserResponse update (Long id, UserRequest request);

    void delete (Long id);

}
