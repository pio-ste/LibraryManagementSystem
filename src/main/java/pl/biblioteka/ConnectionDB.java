package pl.biblioteka;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
    public Connection connection;

    public Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka?useTimezone=true&serverTimezone=UTC", "student", "student"); //Łączenie z bazą danych zawiera adres url do bazy danych login oraz hasło
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
