package file.thousandaire.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import file.thousandaire.domain.UserDetails;
import file.thousandaire.repository.UserDetailsRepository;

@Controller
public class ThousandaireController {
        
    @Autowired
    private HttpSession session;
    @Autowired
    private UserDetailsRepository userDetailsRepository;
     @PostConstruct
    public void init() {
        userDetailsRepository.save(new UserDetails("House", "house@mail.com"));
    }

    @RequestMapping("*")
    public String main() {
        return "redirect:/topics";
    }

    @RequestMapping("/incorrect")
    public String incorrect() {
        return "incorrect";
    }

    @RequestMapping("/finish")
    public String finish() {
        return "finish";
    }

    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public String postData(@ModelAttribute UserDetails details) {
         if (details.equals(session.getAttribute("details"))) {
            userDetailsRepository.save(details);
        }
        return "thanks";
    }
}
