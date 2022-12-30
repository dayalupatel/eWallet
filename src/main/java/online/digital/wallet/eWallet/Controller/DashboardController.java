package online.digital.wallet.eWallet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import online.digital.wallet.eWallet.Model.Transaction;
import online.digital.wallet.eWallet.Model.User;
import online.digital.wallet.eWallet.Model.Wallet;
import online.digital.wallet.eWallet.Service.TransactionService;
import online.digital.wallet.eWallet.Service.UserService;
import online.digital.wallet.eWallet.Service.WalletService;

@Controller
public class DashboardController {
    @Autowired
    UserService userService;
    @Autowired
    WalletService walletService;
    @Autowired
    TransactionService transactionService;

    @GetMapping("dashboard/{email}/{pass}")
    public ModelAndView dashboard(@PathVariable("email") String email, @PathVariable("pass") String password) {
        User user = userService.getUserByEmail(email);

        Wallet wallet = walletService.getWalletByOwner(user.getEmail());
        
        List<User> userList = userService.getAllUsers();
        List<Transaction> transactionList = transactionService.getTransactionsByWallet(wallet);
        
        ModelAndView model = new ModelAndView();

        model.setViewName("dashboard");
        model.addObject("user", user);
        model.addObject("wallet", wallet);
        model.addObject("userList", userList);
        model.addObject("transactions", transactionList);

        return model;
    }
}
