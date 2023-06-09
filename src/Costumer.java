import java.sql.*;
import java.util.Scanner;

public class Costumer extends Account{
    Costumer(Scanner sc , Connection con , PreparedStatement prep , ResultSet rs) {
        scanner = sc;
        connection = con ;
        preparedStatement = prep;
        resultSet = rs ;
    }

    //we have to set some arguments for startCostumer method

    void startCostumer(){
        System.out.println("this is costumers plan which is empty for now ");
    }
    //we have to add methods here
}
