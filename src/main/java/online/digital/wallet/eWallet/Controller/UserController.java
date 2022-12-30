package online.digital.wallet.eWallet.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import online.digital.wallet.eWallet.Model.User;
import online.digital.wallet.eWallet.Service.TransactionService;
import online.digital.wallet.eWallet.Service.UserService;
import online.digital.wallet.eWallet.Service.WalletService;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    WalletService walletService;
    @Autowired
    TransactionService transactionService;


    @PostMapping("/add-user")
    public String addUser(@ModelAttribute User user, HttpSession session) {
        User existedUser = userService.getUserByEmail(user.getEmail());

        if(existedUser!=null) {
            session.setAttribute("err_msg", "Email Already Existed... Try with Different Email Id");
            return "redirect:/signup"; 
        }

        user = userService.createUser(user);

        String redirect = "redirect:/dashboard/"+ user.getEmail() +"/"+user.getPassword(); // redirect to this dashboard
        
        return redirect;
    }

    @PostMapping("/update")
    public String profile(@ModelAttribute User user, HttpSession session) {
        
        User old = userService.getUserByEmail(user.getEmail());
        user.setId(old.getId());

        userService.updateUser(user);

        session.setAttribute("msg", "Your Profile Updated Successfully...");

        String redirect = "redirect:/dashboard/"+ user.getEmail() +"/"+user.getPassword(); // redirect to this dashboard
        
        return redirect;
    }
}
