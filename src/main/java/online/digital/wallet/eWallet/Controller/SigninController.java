package online.digital.wallet.eWallet.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import online.digital.wallet.eWallet.Model.User;
import online.digital.wallet.eWallet.Model.SigninDto;
import online.digital.wallet.eWallet.Service.UserService;

@Controller
public class SigninController {
    @Autowired
    UserService userService;
    
    @GetMapping("/entry")
    public String signMeIn(@ModelAttribute SigninDto sign, HttpSession session) {
        User user = userService.getUserByEmail(sign.getEmail());

        // User Not Exists
        if(user==null) {
            session.setAttribute("err_msg", "No User Exists With this Email Id");
            return "redirect:/signin";
        }

        // ## User (Email) exists ##

        // checking password
        if(!sign.getPassword().equals(user.getPassword())) {
            session.setAttribute("err_msg", "OOPS Incorrect Password !!");
            return "redirect:/signin";
        }

        // All correct Allow to login
        String redirect = "redirect:/dashboard/"+ user.getEmail() +"/"+user.getPassword();

        return redirect;
    }
}
