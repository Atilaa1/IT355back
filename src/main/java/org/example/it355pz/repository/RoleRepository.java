package org.example.it355pz.repository;

import org.example.it355pz.model.Role;
import org.example.it355pz.model.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleType name);

}
