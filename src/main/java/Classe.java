import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Connection;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@Builder
public class Classe implements Interface{
    private int Id;
    private String Libelle;
    private int Nb_exos;

    @Override
    public boolean AlreadyExist(Connection connexion) throws SQLException {
        return false;
    }

    @Override
    public void Insertion(Connection connexion) throws SQLException {

    }
}
