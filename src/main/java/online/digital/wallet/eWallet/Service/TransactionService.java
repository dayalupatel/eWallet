package online.digital.wallet.eWallet.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.digital.wallet.eWallet.Model.Transaction;
import online.digital.wallet.eWallet.Model.TransactionStatus;
import online.digital.wallet.eWallet.Model.Wallet;
import online.digital.wallet.eWallet.Repository.TransactionRepository;
import online.digital.wallet.eWallet.Repository.UserRepository;
import online.digital.wallet.eWallet.Repository.WalletRepository;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    WalletService walletService;

    public Transaction initiateTransaction(String fromUser, String toUser, double amount) {
        Transaction transaction = Transaction.builder()
                                .txn_Id(UUID.randomUUID().toString())
                                .fromUser(fromUser)
                                .toUser(toUser)
                                .amount(amount)
                                .transactionStatus(TransactionStatus.PENDING)
                                .createdOn(new Date().toString()).build();
        
        transactionRepository.save(transaction);

        // UPDATE BALANCE
        Wallet senderWallet = walletService.getWalletByOwner(fromUser);
        if(senderWallet.getBalance() >= amount) {
            // DEDUCT MONEY FROM SENDER WALLET
            Wallet receiverWallet = walletService.getWalletByOwner(toUser);

            walletService.updateBalance(senderWallet, -1*amount);
            walletService.updateBalance(receiverWallet, amount);

            transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
            transactionRepository.save(transaction);

            // add received txn to wallet
            updateTxnList(receiverWallet, transaction);
        } 
        else { 
            // low balance -> TRANSACTION FAILED
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
        }

        // add send txn to wallet
        updateTxnList(senderWallet, transaction);

        return transaction;
    }

    private void updateTxnList(Wallet wallet, Transaction transaction) {
        wallet.getTransactions().add(transaction);
        transaction.setWallet(wallet);
        transactionRepository.save(transaction);
    }


    public List<Transaction> getTransactionsByWallet(Wallet wallet) {
        return transactionRepository.findAllByWallet(wallet);
    }


    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

}
