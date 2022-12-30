package online.digital.wallet.eWallet.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "txns")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "txn_id", nullable = false)
    private String txn_Id;

    @Column(name = "fromUser", nullable = false)
    private String fromUser;

    @Column(name = "toUser", nullable = false)
    private String toUser;


    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "transactionStatus")
    private TransactionStatus transactionStatus;

    @Column(name = "createdOn")
    private String createdOn;

    @ManyToOne
    @JoinColumn
    private Wallet wallet;
}
