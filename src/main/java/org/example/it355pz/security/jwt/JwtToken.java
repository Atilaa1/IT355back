package org.example.it355pz.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class JwtToken implements Serializable {
	private String token;
}