import java.sql.*;
import java.util.Scanner;

public class Costumer extends Account{
    Costumer(Scanner sc , Connection con , PreparedStatement prep , ResultSet rs) {
        scanner = sc;
        connection = con ;
        preparedStatement = prep;
        resultSet = rs ;
    }
    void startCostumer(){}
    void SetAddress(){}
    void SetOrder(){}
    void UseDiscount(){}
    void SendFeedback(){} //for comments
    void ConnectToDelivery(){}
    void ChangeBankInfo(){} //linked to BankAccountInfo class
    void Pay(){}
    void DeActiveOrder(){}
}
