package ru.aminovniaz.project.util;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DbWrapper {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URI = "jdbc:postgresql://127.0.0.1:5432/spring_test";
    private static final String USER = "postgres";
    private static final String PASSWORD = "futureiscoming";

    private static Connection connection = null;

    public static Connection getConnection() {
        if(connection == null){
            connection = createConnection();
        }
        return connection;
    }

    private static Connection createConnection() {
        Connection conn = null;

        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URI, USER, PASSWORD);
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
        }

        return conn;
    }
}
