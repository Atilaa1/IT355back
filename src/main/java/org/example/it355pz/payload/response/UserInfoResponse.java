package org.example.it355pz.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserInfoResponse {
    private int id;
    private String username;
    private String mail;
    private List<String> roles;
}
