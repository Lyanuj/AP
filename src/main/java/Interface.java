import java.sql.*;

public interface Interface {
    boolean AlreadyExist(Connection connexion) throws SQLException;

    void Insertion(Connection connexion) throws SQLException;
}
