import java.util.ArrayList;

public class Admin extends Account{
    String RestaurantName ;
    String RestaurantAddress ;
    // arraylists (or hash maps) below , don't have type . we have to make classes named "food" and "order" and "deliveries" and link them to these arraylists
    ArrayList FoodMenu ; //it can be a hash map too
    ArrayList ListOfOrders ; //it can be a hash map too
    ArrayList ListOfDeliveries ;//it can be a hash map too
    ArrayList ListOfDeliveredOrders ;//it can be a hash map too
    Admin(String name , String pass){
        Password = pass ;
        Name = name ;
    }
    void startAdmin(){}
    void changeMenu (){

    }
    void HireDelivery (){

    }
    void ManageDeliveries (){

    }
    void addPointsToCustomer() {

    }
    void addDiscountToCustomer (){

    }
    void PriceCalc() {

    }
    void ReceiveFeedback() { // it is used for comments and feedbacks

    }
    void AnswerToFeedback(){ // it is used for comments and feedbacks

    }
}
