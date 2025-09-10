package com.danieldev.Learniverse.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotBlank(message = "Correo mal ingresado")
    private String email;
    private String password;
}
