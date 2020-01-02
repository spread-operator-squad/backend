package com.enigma.entities;

import com.enigma.enumeration.PaymentMethod;
import com.enigma.enumeration.TransactionProgress;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private Date dateStart;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private Date dateEnd;
    private TransactionProgress transactionProgress;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @JoinColumn(name = "stores_id")
    private Store stores;

    @Transient
    private Integer storesId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "operator_id")
    private User operator;

    @Transient
    private String operatorId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "customer_id")
    private User customer;

    @Transient
    @Nullable
    private String customerUsername;

    @Transient
    private PaymentMethod type;

    private BigDecimal total;
    private BigDecimal pay;
    private BigDecimal change;

    @OneToMany (mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<TransactionDetail> transactionDetails;

    public Integer getStoresId() {
        if (stores != null) return stores.getId();
        return storesId;
    }

    public String getOperatorId() {
        if (operator != null) return operator.getId();
        return operatorId;
    }

    public String getCustomerUsername() {
        if (customer != null) return  customer.getUsername();
        return customerUsername;
    }
}
