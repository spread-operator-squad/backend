package com.enigma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private String name;
    private String avatar;

    @Transient
    private String ownerId;

    @OneToMany(mappedBy = "stores", cascade = CascadeType.ALL)
    private List<Item> items;

    @OneToMany(mappedBy = "stores", cascade = CascadeType.ALL)
    private List<Services> services;

    @ManyToMany(mappedBy = "stores")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany (mappedBy = "stores", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public String getOwnerId() {
        if (owner != null) return owner.getId();
        return ownerId;
    }
}
