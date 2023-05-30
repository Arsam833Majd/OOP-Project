import java.util.ArrayList;

public class Food {
    String name ;
    boolean Active ;
    double DiscountPercent , DiscountTime , Rating ;
    int ID , Price;
    ArrayList <String > Comments ;
    Food(String name , int price){
        this.name  = name ;
        Price = price ;
    }

}
