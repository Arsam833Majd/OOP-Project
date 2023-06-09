import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in) ;
        Connection connection = null ;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //if the url isn't working for you , use this instead : com.mysql.jdbc.Driver

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop_project", "root", "123456");
        }catch (Exception e){
            System.out.println(e);
        }
        UsersData us = new UsersData(connection , preparedStatement , resultSet , sc) ;
        us.loginOrRegister();
        String address = "D:\\SUT\\graph.txt" ;

        //use the address of graph.txt file on your computer

        Map map = new Map(address);
        //System.out.println(map.shortestPath(1,500,1));
    }
}