package com.enigma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor @Getter @Setter
@Table(name = "address")
public class Address extends Auditable{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String addressName;
    private String latitude;
    private String longitude;

    @Transient
    private Integer locationId;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "location_id")
    private Location location;

    public Integer getLocationId() {
        if (location != null) return location.getId();
        return locationId;
    }

}
