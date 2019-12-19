package com.enigma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private Integer subtotal;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @JoinColumn(name = "transactions_id")
    private Transaction transaction;

    @Transient
    private Integer transactionId;

    private Integer servicesId;
    private Integer itemsId;

    public Integer getTransactionId() {
        if (transaction != null) return transaction.getId();
        return transactionId;
    }
}
