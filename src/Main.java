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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop_project", "root", "123456");
        }catch (Exception e){
            System.out.println(e);
        }
        UsersData us = new UsersData(connection , preparedStatement , resultSet , sc) ;
        us.loginOrRegister();
        Map map = new Map("D:\\SUT\\graph.txt");
        //System.out.println(map.shortestPath(1,500,1));
    }
}