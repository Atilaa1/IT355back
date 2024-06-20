package org.example.it355pz.repository;

import org.example.it355pz.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UsersEntity, Integer> {
    Optional<UsersEntity> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByMail(String email);
}
