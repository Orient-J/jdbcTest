package chapter6.test2;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;

import java.util.Scanner;

public class Runtime {

    public static void main(String[] args) throws ClassNotFoundException, SQLException{

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/userdb", "root", "root@password#007");

        String insertStatement = "INSERT INTO users(fullname, email, password) VALUES (?,?,?) "; 

        PreparedStatement prep = connection.prepareStatement(insertStatement);

        Scanner scan = new Scanner(System.in);

        while(true) {

            System.out.print("Enter Full Name :: ");
            prep.setString(1, scan.nextLine());

            System.out.print("Enter Email :: ");
            prep.setString(2,scan.nextLine());

            System.out.print("Enter Password :: ");
            prep.setString(3, scan.nextLine());

            System.out.println( (prep.executeUpdate() > 0 ) ? "Successfully Insert" : "Syntax Erro or Something Went Wrong!!!" );

            System.out.print("Insert Another Time [yes/no] :: ");
            if( scan.nextLine().equalsIgnoreCase("no") ) break;
        }

        Statement statement = connection.createStatement();

        ResultSet result =  statement.executeQuery("SELECT * FROM users");

        while( result.next() ) {

            System.out.println(
                String.format("%-10d", result.getInt("id")) +
                String.format("%-20s", result.getString("fullname")) +
                String.format("%-30s", result.getString("email")) + 
                String.format("%-10s", result.getString("password")) 
            );
        }

        scan.close();
        statement.close();
        prep.close();
        connection.close();
    }   
}
