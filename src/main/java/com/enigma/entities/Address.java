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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String description;
    private String latitude;
    private String longitude;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private UserDetail userDetail;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "store_id")
    private Store store;
}
