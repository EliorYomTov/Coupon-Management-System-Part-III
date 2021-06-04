package com.elior.couponManagementSystem.security.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse implements Serializable {
    private final String jwt;
}