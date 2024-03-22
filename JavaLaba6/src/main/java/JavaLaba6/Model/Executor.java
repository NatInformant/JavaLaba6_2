package JavaLaba6.Model;

import java.sql.*;
import java.util.Properties;
public class Executor {
    private static Connection connection;
    public static void createConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());

            String url =
                    "jdbc:postgresql://" +  //db type
                    "localhost:" +          //host name
                    "5432/" +               //port
                    "users_database";       //db name
            connection = DriverManager.getConnection(url,"postgres","12345");
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static void checkConnection(){
        if(connection == null){
            createConnection();
        }
    }
    public static void execUpdate(String query) {
        checkConnection();
        try(Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static <T> T execQuery(String query, ResultHandler<T> handler){
        checkConnection();
        T result = null;
        try(Statement statement = connection.createStatement()) {
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            result = handler.handle(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
