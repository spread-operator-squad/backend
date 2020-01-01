package com.enigma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "stores")
public class Store extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Please provide name")
    private String name;

    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL)
    private Address address;

    @Transient
    private String ownerId;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Item> items;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Services> services;

    @ManyToMany(mappedBy = "stores")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany (mappedBy = "stores", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "operatorStore",cascade = CascadeType.ALL)
    private Set<User> operator = new HashSet<>();

    public String getOwnerId() {
        if (owner != null) return owner.getId();
        return ownerId;
    }
}
