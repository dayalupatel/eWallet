package online.digital.wallet.eWallet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import online.digital.wallet.eWallet.Model.User;
import online.digital.wallet.eWallet.Model.Wallet;
import online.digital.wallet.eWallet.Model.Transaction;
import online.digital.wallet.eWallet.Service.TransactionService;
import online.digital.wallet.eWallet.Service.UserService;
import online.digital.wallet.eWallet.Service.WalletService;

@Controller
public class Admin {
    @Autowired
    UserService userService;
    @Autowired
    WalletService walletService;
    @Autowired
    TransactionService transactionService;


    @GetMapping("/get-all-db-tables-imdayalupatel/{pass}")
    public ModelAndView getAllUsers(@PathVariable("pass") String pass){
        ModelAndView model = new ModelAndView();
        
        if(!pass.equals("patel@123")) {
            model.setViewName("error");
            return model;
        }
        
        List<User> users = userService.getAllUsers();
        List<Wallet> wallets = walletService.getAllWallets();
        List<Transaction> txns = transactionService.getAllTransactions();

        model.setViewName("dbtable");
        model.addObject("userList", users);
        model.addObject("walletList", wallets);
        model.addObject("txnList", txns);

        return model;
    }
    
}
