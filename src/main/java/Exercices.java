import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Connection;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@Builder
public class Exercices implements Interface{
    private int Id;
    private Classe Id_classe;
    private int Coef;
    private String Libelle;

    @Override
    public boolean AlreadyExist(Connection connexion) throws SQLException {
        return false;
    }

    @Override
    public void Insertion(Connection connexion) throws SQLException {

    }
}
