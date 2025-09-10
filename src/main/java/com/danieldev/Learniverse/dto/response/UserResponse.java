package com.danieldev.Learniverse.dto.response;

import com.danieldev.Learniverse.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private Rol rol;
}
