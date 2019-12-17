package com.enigma.entities;

import com.enigma.enumeration.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor @Getter @Setter
public class UserDetail extends Auditable{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String name;
    private Integer phoneNumber;
    private Date birthDate;
    private String avatar;
    private Gender gender;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;

    public UserDetail(String name, Integer phoneNumber, Date birthDate, String avatar, Gender gender) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.avatar = avatar;
        this.gender = gender;
    }
}
