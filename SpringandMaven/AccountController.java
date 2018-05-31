
package file.project.controller;



import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

   // @Autowired
   // private PasswordEncoder encoder;

    @RequestMapping(value = "/theform", method = RequestMethod.POST)
    public String passwordForm() {
        return "theform";
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public String changePassword(/*Authentication authentication,*/@RequestParam String name,@RequestParam String password) {
        Account account = accountRepository.findByUsername(name/*authentication.getName()*/);
        if (account == null) {
            return "redirect:/theform";
        }
        account.setUsername(name);
        account.setPassword(password/*encoder.encode(password)*/);
        accountRepository.save(account);

        return "thanks";
    }

}
