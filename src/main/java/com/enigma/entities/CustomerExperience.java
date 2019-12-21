package com.enigma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "customer_experiences")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class CustomerExperience extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String level;
    private Integer point;

    @Transient
    private String userId;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    public String getUserId() {
        if (user != null) return user.getId();
        return userId;
    }

}
