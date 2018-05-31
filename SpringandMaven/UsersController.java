
package sec.project.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class UsersController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping(value = "/done", method = RequestMethod.GET)
    public String loadIt(Model model, @RequestParam (required = true) String name, @RequestParam (required = false) String address) {
        try {
            // Open connection to a database -- do not alter this code
            String databaseAddress = "jdbc:h2:file:./database;DB_CLOSE_DELAY=-1";
            Connection connection = DriverManager.getConnection(databaseAddress, "sa", "");
        if(name != null){
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM USERS");
            // If database has not yet been created, insert content
           // RunScript.execute(connection, new FileReader("sql/database-schema.sql"));
           // RunScript.execute(connection, new FileReader("sql/database-import.sql"));
            // Add the code that first reads the agents from the database, then
            Statement statement = connection.createStatement();
            if (!resultSet.next()){
            String qry = "CREATE DATABASE USERS";
            statement.executeUpdate(qry);
            System.out.println("Database created succesfully...");
            }
            else {
            name = resultSet.getString("name");
            address = resultSet.getString("address");
            String query = "INSERT INTO USERS (name, address) VALUES ('" +name+ "', '" +address+ "')";
            //Statement statement = connection.createStatement();
            // Finally, the program prints the agents in the database again.
            statement.execute(query);
            signupRepository.save(new Signup(name, address));
            resultSet.close();
            connection.close();
            }
        }
        }
        catch (SQLException t) {
            System.err.println(t.getMessage());
        }
        //signupRepository.save(new Signup(name, address));
        model.addAttribute("list", signupRepository.findAll());
        return "getit";
    }
}
 /*String sql = ("SELECT usr FROM User usr  WHERE usr.configurable = TRUE "
              + "AND (" +
                        "lower(usr.name) like lower(:filterText) OR lower(usr.userType.classType.displayName) like lower(:filterText) OR lower(usr.userType.model) like lower(:filterText)"
              +      ")"
              + "");*/
