package JavaLaba6.Executor;

import JavaLaba6.Model.ResultHandler;

import java.sql.*;

import static JavaLaba6.Executor.MyAppLifecycleListener.connection;

public class Executor {
    //обьект, который отвечает за общение с БД
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
