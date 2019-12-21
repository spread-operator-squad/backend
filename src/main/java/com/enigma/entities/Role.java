package com.enigma.entities;

import com.enigma.enumeration.UserRoles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor @Getter @Setter
@Table(name = "roles")
public class Role extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private UserRoles userRoles;

    public Role(UserRoles userRoles) {
        this.userRoles = userRoles;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "userRoles", cascade = CascadeType.MERGE)
    private Set<User> users = new HashSet<>();
}
