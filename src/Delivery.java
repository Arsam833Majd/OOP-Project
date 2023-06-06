import java.sql.*;
import java.util.Scanner;

public class Delivery extends Account{
    Delivery(Scanner sc , Connection con , PreparedStatement prep , ResultSet rs) {
        scanner = sc;
        connection = con ;
        preparedStatement = prep;
        resultSet = rs ;
    }
    void startDelivery(){}
    void ConnectToAdmin(){}
    void ConnectToCustomer(){}
    void ChangeBankInfo(){}
    void FindRoute(){}
    void TimeCalc(){}
}
