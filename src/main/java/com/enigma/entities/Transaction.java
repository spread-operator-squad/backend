package com.enigma.entities;

import com.enigma.enumeration.TransactionProgress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "transactions")
public class Transaction extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date dateStart;
    private Date dateEnd;
    private TransactionProgress transactionProgress;
    private Integer storesId;
    private String operatorId;
    private String customerId;
    private Integer total;
    private Integer pay;
    private Integer change;

    @OneToMany (mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<TransactionDetail> transactionDetails;
}
