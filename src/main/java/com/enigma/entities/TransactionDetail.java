package com.enigma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "transaction_details")
public class TransactionDetail extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer weight;
    private BigDecimal subtotal;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @JoinColumn(name = "transactions_id")
    private Transaction transaction;

    @Transient
    private Integer transactionId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @JoinColumn(name = "services_id")
    private Services services;

    @Transient
    private Integer servicesId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @JoinColumn(name = "item_id")
    private Item item;

    @Transient
    private Integer itemId;

    public Integer getTransactionId() {
        if (transaction != null) return transaction.getId();
        return transactionId;
    }

    public Integer getServicesId() {
        if (services != null) return services.getId();
        return servicesId;
    }

    public Integer getItemId() {
        if (item != null) return item.getId();
        return itemId;
    }
}
