package com.clinic.appointment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequest {

    private String email;
    private String password;
}
