package com.enigma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor @Getter @Setter
@Table(name = "locations")
public class Location extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String type;

    @Transient
    private Integer locationId;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private Set<Location> locations = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "locationId")
    @JsonIgnore
    private Location location;

    public Integer getLocationId() {
        if (location != null) return location.getId();
        return locationId;
    }
}
