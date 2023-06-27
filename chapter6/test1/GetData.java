package chapter6.test1;

import java.sql.DriverManager;
// import java.sql.Driver;

import java.sql.Connection;

import java.sql.Statement;

import java.sql.ResultSet;

import java.sql.SQLException;

public class GetData {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // step 1 : load Driver 

        Class.forName("com.mysql.cj.jdbc.Driver");

        // step 2 : build connection 

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/userdb","root","root@password#007");

        // step 3 : creating a statement
        Statement statement = connection.createStatement();

 
        // step 4 : execute Statment 
        // inserting 
        int insertDone = statement.executeUpdate("INSERT INTO users(fullname,email,password) VALUE ('Asta', 'wizardKing@gmail.com', 'astaKing@111')");
        if(insertDone > 0) System.out.println("Successfully Inserted!");

        // updating
        if(statement.executeUpdate("Update users SET fullname = 'Yuno' WHERE id = 3 ") > 0) System.out.println("Successfully Update!"); 

        // deleting
        if( statement.executeUpdate("DELETE FROM users WHERE fullname = 'asta' ") > 0 ) System.out.println("Successfully Deleted!!!");

        // step 5 : receive data retrieved from database
        ResultSet result = statement.executeQuery("SELECT * FROM users");

        while( result.next() ) {

            System.out.println(
                formatResult(
                    result.getInt("id"),
                    result.getString("fullname")  , 
                    result.getString("email"),
                    result.getString("password") 
                    )
            );
        }

        // step 6 : closing
        statement.close();
        connection.close();

    }

    private static String formatResult(int id, String fullname, String email, String password) {

        return String.format("%-10d", id) + String.format("%-20s", fullname) + String.format("%-30s", email) + String.format("%-20s", password) ;
    }
}