package online.digital.wallet.eWallet.Repository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import online.digital.wallet.eWallet.Model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Wallet findByOwner(String owner);
}
