package com.enigma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
@NoArgsConstructor
@Getter
@Setter
public class Item extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private BigDecimal price;

    @Transient
    private Integer storeId;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "store_id")
    private Store stores;

    public Integer getStoreId() {
        if (stores != null) return stores.getId();
        return storeId;
    }
}
