package org.example.it355pz.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.it355pz.model.enums.RoleType;

import javax.persistence.*;

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
    @Column(name = "naziv", nullable = false, length = 20)
    private RoleType name;

}