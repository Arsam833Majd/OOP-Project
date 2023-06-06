import java.sql.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Account {
    Scanner scanner ;
    boolean loggedIn ;
    Connection connection ;
    PreparedStatement preparedStatement ;
    ResultSet resultSet ;
    static boolean checkPattern(String pass){
        if(Pattern.matches("\\w{8,20}" , pass))
            return true ;
        return false ;
    }
    void ChangePassword(String newPass){

    }
}
