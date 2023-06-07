import java.sql.*;
import java.util.Scanner;

public class UsersData {
    Admin admin ;
    Costumer costumer;
    Delivery delivery ;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Scanner scanner ;
    UsersData(Connection con , PreparedStatement prep , ResultSet rs , Scanner sc){
        connection = con ;
        preparedStatement = prep ;
        resultSet = rs ;
        scanner = sc ;
        admin = new Admin(scanner , connection , preparedStatement , resultSet) ;
        costumer = new Costumer(scanner , connection , preparedStatement , resultSet);
        delivery = new Delivery(scanner , connection , preparedStatement , resultSet) ;
    }
    void loginOrRegister() {
        boolean entered = false;
        while (!entered) {
            System.out.println("type the role you want to login or register with (Admin , Costumer , Delivery)");
            boolean correctRole = false;
            String role = "";
            while (!correctRole) {
                System.out.println("role :");
                role = scanner.nextLine();
                if (role.equals("Admin") || role.equals("Costumer") || role.equals("Delivery"))
                    correctRole = true;
                else
                    System.out.println("choose the correct role !");
            }
            System.out.println("Login or Register :");
            String answer = scanner.nextLine();
            if (answer.equals("login")) {
                boolean loggedIn = false;
                while (!loggedIn) {
                    System.out.println("name :");
                    String name = scanner.nextLine();
                    int userID = searchUser(name);
                    System.out.println("password :");
                    String pass = scanner.nextLine();
                    if(userID != -1) {
                        if(checkPass(pass,userID)){
                            loggedIn = true ;
                            System.out.println("welcome !");
                            Login(role , userID);
                        }else
                            System.out.println("password is incorrect ! try again");
                    }else
                        System.out.println("your id is not found ! try again");
                }
            } else if (answer.equals("register")) {
                boolean registered = false;
                while (!registered) {
                    System.out.println("choose a name and password , your password must contain at least 8 alphanumeric characters");
                    System.out.println("name :");
                    String name = scanner.nextLine();
                    System.out.println("password :");
                    String pass = scanner.nextLine();
                    if (Account.checkPattern(pass)) {
                        if (searchUser(name) != -1)
                            System.out.println("your name is taken , please choose a different name");
                        else {
                            switch(role){
                                case "Admin" :{
                                    System.out.println("Restaurant name :");
                                    String RName = scanner.nextLine() ;
                                    System.out.println("Restaurant Location :");
                                    String RLoc = scanner.nextLine() ;
                                    AddAdmin(name , pass , RName , RLoc);
                                    break;
                                }
                                case "Costumer" : {
                                    AddCostumer();
                                    break;
                                }
                                case "Delivery" :{
                                    AddDelivery();
                                    break;
                                }
                            }
                            entered = true ;
                            registered = true ;
                        }
                    } else {
                        System.out.println("your password has incorrect pattern , try again");
                    }
                }
            }
        }
    }
    int searchUser(String name){
        int id ;
        try {
            preparedStatement = connection.prepareStatement("select id from auth_tbl where username = ?;");
            preparedStatement.setString(1 , name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next() ;
            id = resultSet.getInt(1);
        }catch (Exception e){
            id = -1 ;
        }
        return id ;
    }
    boolean checkPass(String pass , int id){
        boolean checked = false ;
        try{
            preparedStatement = connection.prepareStatement("select password from auth_tbl where id = ?;");
            preparedStatement.setInt(1 , id);
            resultSet = preparedStatement.executeQuery() ;
            resultSet.next();
            String realPass = resultSet.getString(1) ;
            if(pass.equals(realPass))
                checked = true;
        }catch (Exception e){
            System.out.println(e);
        }
        return checked;
    }
    int get_restId(int id){
        int restId ;
        try {
            preparedStatement = connection.prepareStatement("select username from auth_tbl where id = ? ;");
            preparedStatement.setInt(1 , id);
            resultSet = preparedStatement.executeQuery() ;
            resultSet.next();
            String rAdmin = resultSet.getString(1);
            ResultSet second ;
            preparedStatement = connection.prepareStatement("select id from restaurant_tbl where radmin = ?;");
            preparedStatement.setString(1, rAdmin);
            second = preparedStatement.executeQuery();
            second.next();
            restId = second.getInt(1);
        }catch (Exception e){
            restId = -1 ;
            System.out.println(e);
        }
        return restId;
    }
    void Login(String role , int id){
        switch (role) {
            case "Admin" :{
                admin.loggedIn = true;
                admin.startAdmin(get_restId(id));
                break;
            }
            case "Costumer" :{
                costumer.loggedIn = true ;
                costumer.startCostumer();
                break;
            }
            case "delivery" :{
                delivery.loggedIn = true ;
                delivery.startDelivery();
            }
        }
        loginOrRegister();
    }
    void AddAdmin(String name , String pass , String RName , String RLocation){
        try{
            preparedStatement = connection.prepareStatement("insert into auth_tbl (username , password , role , money) values (?,?,1,0);") ;
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,pass);
            preparedStatement.executeUpdate() ;
            preparedStatement = connection.prepareStatement("insert into restaurant_tbl (rname , radmin , location) values (?,?,?);");
            preparedStatement.setString(1,RName);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3, RLocation);
            preparedStatement.executeUpdate() ;
            preparedStatement = connection.prepareStatement("select id from auth_tbl where username = ?;");
            preparedStatement.setString(1 , name);
            resultSet = preparedStatement.executeQuery() ;
            resultSet.next();
            System.out.println("Registered successfully !");
            admin.loggedIn = true ;
            admin.startAdmin(get_restId(resultSet.getInt(1)));
            loginOrRegister();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    void AddCostumer(){
        //loginOrRegister();
    }
    void AddDelivery(){
        //loginOrRegister();
    }
}
