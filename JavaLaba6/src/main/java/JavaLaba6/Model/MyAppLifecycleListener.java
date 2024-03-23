package JavaLaba6.Model;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class MyAppLifecycleListener implements ServletContextListener {
    public static Connection connection;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        connection = getConnection();
    }
    public Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());

            String url =
                    "jdbc:postgresql://" +  //db type
                            "localhost:" +          //host name
                            "5432/" +               //port
                            "users_database";       //db name
            return DriverManager.getConnection(url,"postgres","12345");
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        connection=null;
    }
}
