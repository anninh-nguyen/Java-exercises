package Core;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private static Connection connection;

    private DatabaseConnectionManager() {
        initDbConnection();
    }

    public static DatabaseConnectionManager getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection.isClosed()) { initDbConnection(); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public void initDbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://103.18.6.210:3306/rnoto627nv_ninhna?autoReconnect=true", 
                                                    "rnoto627nv_GNZH", 
                                                    "6wdG!6nJNbJ_");
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}