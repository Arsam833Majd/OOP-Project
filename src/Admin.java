import java.sql.*;
import java.util.Scanner;

public class Admin extends Account {
    Admin(Scanner sc , Connection con , PreparedStatement prep , ResultSet rs) {
        scanner = sc;
        connection = con ;
        preparedStatement = prep;
        resultSet = rs ;
    }

    void startAdmin(int restId) {
        System.out.println("this is admin panel !");
        int foodId = -1 , commentId ;
        String x = scanner.nextLine() ;
        if(x.equals("logout"))
            loggedIn = false ;
        String page = "restaurant" ;
        String [] command ;
        while(loggedIn) {
            command = x.split(" ") ;
            switch (page) {
                case "restaurant": {
                    switch (command[0]){
                        case "show" :{
                            switch (command[1]){
                                case "location":{
                                    System.out.println("restaurant location in graph is node number "+show_loc(restId)) ;
                                    break;
                                }
                                case "foodtype" :{
                                    System.out.println("restaurant food type is "+show_FType(restId));
                                    break;
                                }
                                case "order" :{
                                    System.out.println("order history :");
                                    display_Orders(restId , false);
                                    break;
                                }
                            }
                            break;
                        }
                        case "edit" :{
                            switch(command[1]){
                                case "location" :{
                                    System.out.println("type new node number for location :");
                                    edit_loc(restId , Integer.parseInt(scanner.nextLine()));
                                    System.out.println("location edited successfully !");
                                    break;
                                }
                                case "foodtype" :{
                                    if(checkActive(restId))
                                        System.out.println("there are some active orders , you can't edit food type right now , try later");
                                    else {
                                        System.out.println("ARE YOU SURE YOU WANT TO CHANGE YOUR RESTAURANT TYPE?");
                                        if(scanner.nextLine().equals("yes")) {
                                            String types = "";
                                            for (int i = 2; i < command.length; i++)
                                                types = types + command[i];
                                            edit_FType(restId, types.split(" "));
                                            System.out.println("food types edited successfully !");
                                        }
                                    }
                                    break;
                                }
                                case "food" :{
                                    foodId = Integer.parseInt(command[2]) ;
                                    if(command[3].equals("name")){
                                        edit_foodName(foodId , command[4]);
                                        System.out.println("food name edited successfully !");
                                    }else if (command[3].equals("price")){
                                        edit_foodPrice(foodId , Integer.parseInt(command[4]));
                                        System.out.println("food price edited successfully !");
                                    }
                                    break;
                                }
                                case "order" :{
                                    int orderId = Integer.parseInt(command[2]) ;
                                    System.out.println("choose which property you want to edit (status or delivery time) :");
                                    switch (scanner.nextLine()){
                                        case "status" :{
                                            System.out.println("choose new status (sent or ready to sent or not ready) :");
                                            switch (scanner.nextLine()){
                                                case "sent":{
                                                    edit_orderStatus(orderId , 2);
                                                    System.out.println("edited !");
                                                    break;
                                                }
                                                case "ready to sent":{
                                                    edit_orderStatus(orderId , 3);
                                                    System.out.println("edited !");
                                                    break;
                                                }
                                                case "not ready":{
                                                    edit_orderStatus(orderId , 1);
                                                    System.out.println("edited !");
                                                    break;
                                                }
                                                default:{
                                                    System.out.println("invalid !");
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                        case "delivery time" :{
                                            System.out.println("whats new delivery time ?");
                                            edit_orderTime(orderId , scanner.nextInt());
                                            System.out.println("edited !");
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                            break;
                        }
                        case "add" :{
                            String name = "";
                            for (int i = 2; i < command.length; i++)
                                name = name + command[i] ;
                            add_food(restId , name , Integer.parseInt(command[3]));
                            System.out.println("food added successfully !");
                            break;
                        }
                        case "delete" :{
                            foodId = Integer.parseInt(command[2]) ;
                            if(check_ordering(foodId)){
                                System.out.println("ARE YOU SURE YOU WANT TO DELETE THIS FOOD?");
                                if(scanner.nextLine().equals("yes")){
                                    delete_food(foodId);
                                    System.out.println("food deleted successfully !");
                                }
                            }else
                                System.out.println("the selected food is being ordered , please try later");
                            break;
                        }
                        case "active" :{
                            Active_food(Integer.parseInt(command[2]));
                            System.out.println("food is active !");
                            break;
                        }
                        case "deactive" :{
                            foodId = Integer.parseInt(command[2]) ;
                            if(check_ordering(foodId)){
                                DeActive_food(foodId);
                                System.out.println("food deleted successfully !");
                            }else
                                System.out.println("the selected food is being ordered , please try later");
                            break;
                        }
                        case "discount" :{
                            foodId = Integer.parseInt(command[2]) ;
                            if(checkDis(foodId)){
                                int disPer = Integer.parseInt(command[3]) ;
                                if(disPer <= 50){
                                    discount(foodId , disPer , Integer.parseInt(command[4]));
                                    System.out.println("discounted successfully !");
                                }else
                                    System.out.println("too much discount !");
                            }else
                                System.out.println("there is already a discount for this food !");
                            break;
                        }
                        case "select" :{
                            if(command[1].equals("menu")){
                                System.out.println("the menu :");
                                select_Menu(restId);
                            }else if(command[1].equals("food")){
                                foodId = Integer.parseInt(command[2]) ;
                                page = "food" ;
                                break;
                            }
                            break;
                        }
                        case "display" :{
                            System.out.println("open order :");
                            display_Orders(restId , true);
                            break;
                        }
                        case "logout" :{
                            loggedIn = false ;
                            break;
                        }
                        default:{
                            System.out.println("invalid command !");
                            break;
                        }
                    }
                    break;
                }
                case "food": {
                    switch(command[1]){
                        case "display" :{
                            if(command[2].equals("ratings")){
                                System.out.println("ratings are :");
                                display_rating(foodId);
                            }else if(command[2].equals("comments")){
                                System.out.println("comments are :");
                                display_comment(foodId);
                            }
                            break;
                        }
                        case "add" :{
                            commentId = Integer.parseInt(command[3]) ;
                            System.out.println("type your message (when you are finished type \"end\")");
                            String message = scanner.nextLine();
                            while(!scanner.nextLine().equals("end"))
                                message = message+scanner.nextLine() ;
                            add_response(commentId , message);
                            System.out.println("responded successfully !");
                            break;
                        }
                        case "edit" :{
                            commentId = Integer.parseInt(command[3]) ;
                            System.out.println("this was your message :");
                            display_response(commentId);
                            System.out.println("type your new message (when you are finished type \"end\")");
                            String message = scanner.nextLine();
                            while(!scanner.nextLine().equals("end"))
                                message = message+scanner.nextLine() ;
                            edit_response(commentId , message);
                            System.out.println("response edited successfully !");
                            break;
                        }
                        case "return" :{
                            page = "restaurant" ;
                            break;
                        }
                        default:{
                            System.out.println("invalid command !");
                            break;
                        }
                    }
                    break;
                }
                default:{
                    break;
                }
            }
            x = scanner.nextLine() ;
        }
    }

    int show_loc(int restId) {
        int loc ;
        try {
            preparedStatement = connection.prepareStatement("select location from restaurant_tbl where id = ?; ");
            preparedStatement.setInt(1 , restId);
            resultSet = preparedStatement.executeQuery() ;
            resultSet.next() ;
            loc = resultSet.getInt(1) ;
        }catch (Exception e){
            loc = -1 ;
            System.out.println(e);
        }
        return loc ;
    }

    void edit_loc(int restId , int newLoc) {
        try{
            preparedStatement = connection.prepareStatement("update restaurant_tbl set location = ? where id = ?;");
            preparedStatement.setInt(2 , restId);
            preparedStatement.setInt(1 , newLoc);
            preparedStatement.executeUpdate();
        }catch (Exception  e){
            System.out.println(e);
        }
    }

    String show_FType(int restId) {
        String fType ;
        try{
            preparedStatement = connection.prepareStatement("select typeName from restauranttype_tbl where restaurantId = ? ;");
            preparedStatement.setInt(1 , restId);
            resultSet = preparedStatement.executeQuery() ;
            resultSet.next() ;
            fType = resultSet.getString(1) ;
        }catch (Exception e){
            fType = "none" ;
            System.out.println(e);
        }
        return fType ;
    }

    boolean checkActive(int restID){
        boolean active = false ;
        try {
            preparedStatement = connection.prepareStatement("select isOrder from food_tbl where restaurantId = ?;");
            preparedStatement.setInt(1,restID);
            resultSet = preparedStatement.executeQuery() ;
            while(resultSet.next()){
                if(resultSet.getString(1).equals("yes")){
                    active = true ;
                    break;
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return active ;
    }

    void edit_FType(int restId , String [] newFTypes) {
        try{
            preparedStatement = connection.prepareStatement("delete from restauranttype_tbl where restaurantId = ?;");
            preparedStatement.setInt(1 , restId);
            preparedStatement.executeUpdate() ;
            for (String x: newFTypes) {
                preparedStatement = connection.prepareStatement("insert into restauranttype_tbl (restaurantId , typeName) values (?,?);");
                preparedStatement.setInt(1, restId);
                preparedStatement.setString(2 , x);
                preparedStatement.executeUpdate();
            }
            preparedStatement = connection.prepareStatement("delete from food_tbl where restaurantId = ?;");
            preparedStatement.setInt(1 , restId);
            preparedStatement.executeUpdate() ;
        }catch (Exception e){
            System.out.println(e);
        }
    }

    void select_Menu(int restId){
        try{
            preparedStatement = connection.prepareStatement("select * from food_tbl where restaurantId = ?;") ;
            preparedStatement.setInt(1, restId);
            resultSet = preparedStatement.executeQuery() ;
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getInt(3)+" "+
                        resultSet.getInt(4)+" "+resultSet.getInt(5)+" "+resultSet.getString(6));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    void edit_foodName(int foodId, String newName){
        try{
            preparedStatement = connection.prepareStatement("update food_tbl set foodName = ? where id = ?;");
            preparedStatement.setString(1 , newName);
            preparedStatement.setInt(2 , foodId);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    void edit_foodPrice(int foodId , int newPrice){
        try {
            preparedStatement = connection.prepareStatement("update food_tbl set price = ? where id = ?;");
            preparedStatement.setInt(1 , newPrice);
            preparedStatement.setInt(2 , foodId);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //we should specify a random id for each food
    void add_food(int restId ,String name , int price){
        try{
            preparedStatement = connection.prepareStatement("insert into food_tbl (foodName , price , discount , restaurantId , discountTime , isActive , isOrder) values (?,?,0,?,0,'yes','no') ;");
            preparedStatement.setString(1 , name);
            preparedStatement.setInt(2 , price);
            preparedStatement.setInt(3,restId);
            preparedStatement.executeUpdate() ;
        }catch (Exception e){
            System.out.println(e);
        }
    }

    boolean check_ordering(int foodId){
        boolean ordering = false ;
        try {
            preparedStatement = connection.prepareStatement("select isOrder from food_tbl where id = ?;");
            preparedStatement.setInt(1, foodId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if(resultSet.getString(1).equals("yes"))
                ordering = true ;
        }catch (Exception e){
            System.out.println(e);
        }
        return ordering ;
    }

    void delete_food(int foodId){
        try{
            preparedStatement = connection.prepareStatement("delete from food_tbl where id = ?;");
            preparedStatement.setInt(1, foodId);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    boolean check_activation(int foodId){
        boolean active = false ;
        try{
            preparedStatement = connection.prepareStatement("select isACtive from food_tbl where id = ?;");
            preparedStatement.setInt(1 , foodId);
            resultSet = preparedStatement.executeQuery() ;
            resultSet.next() ;
            if(resultSet.getString(1).equals("yes"))
                active = true ;
        }catch (Exception e){
            System.out.println(e);
        }
        return active ;
    }

    void Active_food(int foodId){
        try{
            preparedStatement = connection.prepareStatement("update food_tbl set isActive = ? where id = ?;");
            preparedStatement.setString(1, "yes");
            preparedStatement.setInt(2, foodId);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    void DeActive_food(int foodId){
        try{
            preparedStatement = connection.prepareStatement("update food_tbl set isActive = ? where id = ?;");
            preparedStatement.setString(1, "no");
            preparedStatement.setInt(2, foodId);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    boolean checkDis(int foodId){
        boolean haveDis = false ;
        try{
            preparedStatement = connection.prepareStatement("select discount from foot_tbl where id = ?;");
            preparedStatement.setInt(1 , foodId);
            resultSet = preparedStatement.executeQuery() ;
            resultSet.next() ;
            if(resultSet.getInt(1) != 0)
                haveDis = true ;
        }catch (Exception e){
            System.out.println(e);
        }
        return haveDis ;
    }

    void discount(int foodId , int disPer , int disTime){
        try{
            preparedStatement = connection.prepareStatement("update food_tbl set discount = ? , discountTime = ? where id = ?; ");
            preparedStatement.setInt(1, disPer);
            preparedStatement.setInt(2 , disTime);
            preparedStatement.setInt(3, foodId);
            preparedStatement.executeUpdate() ;
        }catch (Exception e){
            System.out.println(e);
        }
    }

    void display_rating(int foodId){
        try{
            preparedStatement = connection.prepareStatement("select rating , userId from scores_tbl where foodId = ?;");
            preparedStatement.setInt(1 , foodId);
            resultSet = preparedStatement.executeQuery() ;
            ResultSet second ;
            while(resultSet.next()){
                preparedStatement = connection.prepareStatement("select username from auth_tbl where id = ?;");
                preparedStatement.setInt(1 , resultSet.getInt(2));
                second = preparedStatement.executeQuery() ;
                System.out.println(second.getString(1)+" rating : "+resultSet.getInt(1));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    void display_comment(int foodId){
        try{
            preparedStatement = connection.prepareStatement("select id , userId , comment from comments_tbl where foodId = ?;");
            preparedStatement.setInt(1 , foodId);
            resultSet = preparedStatement.executeQuery() ;
            ResultSet second ;
            while(resultSet.next()) {
                preparedStatement = connection.prepareStatement("select username from auth_tbl where id = ?;");
                preparedStatement.setInt(1 , resultSet.getInt(2));
                second = preparedStatement.executeQuery() ;
                System.out.println(second.getString(1)+" said : "+resultSet.getString(3)+"comment id : "+resultSet.getInt(2));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    void display_response(int commentId){
       try{
           preparedStatement = connection.prepareStatement("select message from response_tbl where commentId = ?;");
           preparedStatement.setInt(1 , commentId);
           resultSet = preparedStatement.executeQuery() ;
           resultSet.next();
           System.out.println(resultSet.getString(1));
       }catch (Exception e){
           System.out.println(e);
       }
    }

    void add_response(int commentId , String response){
        try{
            preparedStatement = connection.prepareStatement("insert into response_tbl (commentId , message) values (?,?);");
            preparedStatement.setInt(1 , commentId);
            preparedStatement.setString(2, response);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    void edit_response(int commentId , String newResponse){
        try{
            preparedStatement = connection.prepareStatement("update response_tbl set message = ? where commentId = ?;");
            preparedStatement.setInt(2 , commentId);
            preparedStatement.setString(1, newResponse);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    void display_Orders(int restId , boolean open){
        try{
            if(open)
                preparedStatement = connection.prepareStatement("select username , foodId from order_tbl where restaurantId = ? and statusId = 1 ;");
            else
                preparedStatement = connection.prepareStatement("select username , foodId from order_tbl where restaurantId = ? and statausId <> 1;");
            preparedStatement.setInt(1 , restId);
            resultSet = preparedStatement.executeQuery() ;
            ResultSet second ;
            String[] orders ;
            while(resultSet.next()){
                System.out.println(resultSet.getString(1)+" orders :");
                orders = resultSet.getString(2).split(" ") ;
                for (String i:orders) {
                    preparedStatement = connection.prepareStatement("select foodName from food_tbl where id = ?;");
                    preparedStatement.setInt(1 ,Integer.parseInt(i));
                    second = preparedStatement.executeQuery() ;
                    System.out.println(second.getString(1));
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    void edit_orderStatus(int orderId , int newStatus){
        try{
            preparedStatement = connection.prepareStatement("update order_tbl set statusId = ? where id = ?");
            preparedStatement.setInt(1 , newStatus);
            preparedStatement.setInt(2 , orderId);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    void edit_orderTime(int orderId , int newTime){
        try{
            preparedStatement = connection.prepareStatement("update order_tbl set deliveryTime = ? where id = ?");
            preparedStatement.setInt(1 , newTime);
            preparedStatement.setInt(2 , orderId);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
