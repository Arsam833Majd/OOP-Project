import java.util.ArrayList;

public class UsersData {
    ArrayList <Admin> admins ;
    ArrayList <Costumer> costumers ;
    ArrayList <Delivery> deliveries ;
    UsersData(){
        admins = new ArrayList<>() ;
        costumers = new ArrayList<>( );
        deliveries = new ArrayList<>() ;
    }
    int searchUser(String Role , String name){
        int id = -1;
        switch (Role){
            case "Admin" :{
                for (Admin i:admins)
                    if(i.Name.equals(name)){
                        id = i.ID ;
                        break;
                    }
                break;
            }
            case "Costumer" :{
                for (Costumer i:costumers)
                    if(i.Name.equals(name)){
                        id = i.ID ;
                        break;
                    }
                break;
            }
            case "Delivery" :{
                for (Delivery i:deliveries)
                    if(i.Name.equals(name)){
                        id = i.ID ;
                        break;
                    }
                break;
            }
            default:
                break;
        }
        return id ;
    }
    void register(String role , String name , String pass){
        System.out.println("registered successfully !");
        switch(role){
            case "Admin" :{
                Admin admin = new Admin(name, pass);
                admins.add(admin) ;
                admin.startAdmin();
                break;
            }
            case "Costumer" :{
                Costumer costumer = new Costumer(name, pass) ;
                costumers.add(costumer) ;
                costumer.startCostumer();
                break;
            }
            case "Delivery" :{
                Delivery delivery = new Delivery(name , pass) ;
                deliveries.add(delivery) ;
                delivery.startDelivery();
                break;
            }
            default:
                break;
        }
    }
}
