import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
@Data
@AllArgsConstructor
@Builder
public class Concours implements Interface{
    private int id;
    private Juge Id_Juge;
    private Club Id_Club;
    private String Nom;
    private Date Debut_Date, Fin_Date;

    @Override
    public boolean AlreadyExist(Connection connexion) throws SQLException {
        return false;
    }

    @Override
    public void Insertion(Connection connexion) throws SQLException {

    }
}
