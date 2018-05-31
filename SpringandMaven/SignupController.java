package sec.project.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Controller
@RestController
@Configuration
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;
    
    @PostConstruct
    public void init() {
        // add some content to the repository
        //if(signupRepository.findAll()==null){
        signupRepository.save(new Signup("Jhon", "Street1"));
        signupRepository.save(new Signup("Paul", "Street2"));
        signupRepository.save(new Signup("Steven", "Street3"));
        
    }
    
    
   /* @Bean
    public WebMvcConfigurerAdapter forwardToIndex() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                // forward requests to /admin and /user to their index.html
                registry.addViewController("/admin").setViewName(
                        "forward:/admin/index.html");
                registry.addViewController("/user").setViewName(
                        "forward:/user/index.html");
            }
        };
    }*/
        
    @RequestMapping("*")
    public String home(){
        return "form";/*"redirect:/form";*/
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }
    /*@RequestMapping(value = "/form", method = RequestMethod.GET)
    public String theform() {
        return "theform";
    }*/


  @RequestMapping("/form")
  void handle(HttpServletResponse response) throws IOException {
    response.sendRedirect("/theform");
  }


    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(Model model, @RequestParam (required = true) String name, @RequestParam (required = true) String address) {
        if(name.equalsIgnoreCase("emptyn")){
            return "theform";
        }
        try {
            String databaseAddress = "jdbc:h2:file:./database;DB_CLOSE_DELAY=-1";
            Connection connection = DriverManager.getConnection(databaseAddress, "sa", "");
                Statement statement = connection.createStatement();
                String qry = "CREATE DATABASE USERS";
                statement.executeUpdate(qry);
                System.out.println("Database created succesfully...");
                String query = "INSERT INTO USERS (name, address) VALUES ('" +name+ "', '" +address+ "')";
                statement.execute(query);
                signupRepository.save(new Signup(name, address));
                connection.close();
        }
        catch (SQLException t) {
            System.err.println(t.getMessage());
        }
        signupRepository.save(new Signup(name, address));
       // model.addAttribute("list", signupRepository.findAll());
        return "done";
    }
}
/*String sql = "SELECT usr FROM User usr WHERE usr.configurable = TRUE";

for(String word : filterText.split(",")) {
                sql += " AND (lower(usr.name) like lower(:" + word + ") OR lower(usr.userType.classType.displayName) like lower(:" + word + ") OR lower(usr.userType.model) like lower(:" + word + "))";
}*/
 //http://www.royabubakar.com/blog/2014/01/19/sql-injection-in-java-web-application/
        //https://www.perspectiverisk.com/real-world-xss-attacks-1-introduction-key-javascript-principles/
        //http://stackoverflow.com/questions/30431035/spring-jpa-repository-dynamic-query
        /*@Query("SELECT usr FROM User usr  WHERE usr.configurable = TRUE "
              + "AND (" +
                        "lower(usr.name) like lower(:filterText) OR lower(usr.userType.classType.displayName) like lower(:filterText) OR lower(usr.userType.model) like lower(:filterText)"
              +      ")"
              + "")
  public List<User> findByFilterText(@Param("filterText") String filterText, Sort sort);*/