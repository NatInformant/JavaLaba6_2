package JavaLaba6.Model;

import java.sql.*;

import static JavaLaba6.Model.MyAppLifecycleListener.connection;

public class Executor {
    public static void execUpdate(String query) {

        try(Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static <T> T execQuery(String query, ResultHandler<T> handler){
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
