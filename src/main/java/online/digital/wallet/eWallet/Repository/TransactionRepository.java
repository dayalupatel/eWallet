package online.digital.wallet.eWallet.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import online.digital.wallet.eWallet.Model.Transaction;
import online.digital.wallet.eWallet.Model.Wallet;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByWallet(Wallet wallet);
}
