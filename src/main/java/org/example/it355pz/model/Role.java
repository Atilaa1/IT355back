package org.example.it355pz.model;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.it355pz.model.enums.RoleType;
import jakarta.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, length = 20)
    private RoleType name;

}