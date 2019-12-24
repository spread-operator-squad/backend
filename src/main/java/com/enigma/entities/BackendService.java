package com.enigma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "mst_backend_services")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BackendService extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String endPoint;
    private String httpMethod;
    private String code;

    @Transient
    private Integer roleId;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "role_id")
    private Role role;

    public Integer getRoleId() {
        if(role != null) return role.getId();
        return roleId;
    }
}
