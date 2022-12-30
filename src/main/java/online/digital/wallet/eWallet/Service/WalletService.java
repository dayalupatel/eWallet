package online.digital.wallet.eWallet.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.digital.wallet.eWallet.Model.User;
import online.digital.wallet.eWallet.Model.Wallet;
import online.digital.wallet.eWallet.Repository.WalletRepository;

@Service
public class WalletService {
    @Autowired
    WalletRepository walletRepository;

    public Wallet createWallet(User user) {
        int surprizeBalance = (int) (Math.random()*100);

        Wallet wallet = Wallet.builder()
                    .user(user)
                    .balance(surprizeBalance)
                    .owner(user.getEmail()).build();
        

        walletRepository.save(wallet);
        return wallet;
    }

    public Wallet getWalletByOwner(String owner) {
        return walletRepository.findByOwner(owner);
    }

    public void updateBalance(Wallet wallet, double amount) {
        double updatedBalance = wallet.getBalance() + amount;

        wallet.setBalance(updatedBalance);
        
        walletRepository.save(wallet);
    }

    public List<Wallet> getAllWallets(){
        return walletRepository.findAll();
    }
}
