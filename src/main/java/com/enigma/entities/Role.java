package com.enigma.entities;

import com.enigma.enumeration.UserRoles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @Getter @Setter
@Table(name = "roles")
public class Role extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private UserRoles userRoles;

    @ManyToMany(mappedBy = "roles")
    List<User> users = new ArrayList<>();
}
