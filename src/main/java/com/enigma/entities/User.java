package com.enigma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor @Getter @Setter
@Table(name = "users")
public class User extends Auditable{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String username;
    private String password;
    private Boolean isActive = false;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserDetail userDetail;

    @Transient
    private Set<String> roles = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Wallet> wallets;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private  List<CustomerExperience> customerExperiences;

    @ManyToMany(mappedBy = "users")
    private Set<Review> reviews = new HashSet<>();
}
