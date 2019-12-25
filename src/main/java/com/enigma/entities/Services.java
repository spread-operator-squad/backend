package com.enigma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "services")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Services extends Auditable {
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

    @OneToMany (mappedBy = "services", cascade = CascadeType.ALL)
    private List<TransactionDetail> transactionDetails;
}
