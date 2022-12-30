package online.digital.wallet.eWallet.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import online.digital.wallet.eWallet.Model.Transaction;
import online.digital.wallet.eWallet.Model.User;
import online.digital.wallet.eWallet.Model.PaymentDto;
import online.digital.wallet.eWallet.Service.TransactionService;
import online.digital.wallet.eWallet.Service.UserService;
import online.digital.wallet.eWallet.Service.WalletService;

@Controller
public class PaymentController {
    @Autowired
    UserService userService;
    @Autowired
    WalletService walletService;
    @Autowired
    TransactionService transactionService;
    
    @PostMapping("/payment")
    public String makeTransaction(@ModelAttribute PaymentDto payment, HttpSession session) 
    {
        Transaction txn = transactionService.initiateTransaction(payment.getFromUser(), payment.getToUser(), payment.getAmount());
        
        String msg = "Your Payment " + txn.getTransactionStatus();

        session.setAttribute("msg", msg);

        User user = userService.getUserByEmail(payment.getFromUser());

        String redirect = "redirect:/dashboard/"+ user.getEmail() +"/"+user.getPassword(); // redirect to this dashboard
        
        return redirect;
    }


}

