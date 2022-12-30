package online.digital.wallet.eWallet.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageServer {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }
    
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
    
    @GetMapping("/error")
    public String handleError() {
        return "error";
    }

}
