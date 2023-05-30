import java.util.ArrayList;

public class Costumer extends Account{
    ArrayList OrderHistory ;
    boolean isOrdering ; /*(its true when customer is ordering )*/
    ArrayList Orders ;
    ArrayList Discounts ;
    int Points ;
    Costumer(String name , String pass){
        Password = pass ;
        Name = name ;
    }
    void startCostumer(){} ;
    void SetAddress(){}
    void SetOrder(){}
    void UseDiscount(){}
    void SendFeedback(){} //for comments
    void ConnectToDelivery(){}
    void ChangeBankInfo(){} //linked to BankAccountInfo class
    void Pay(){}
    void DeActiveOrder(){}
}
