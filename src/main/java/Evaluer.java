import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Connection;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@Builder
public class Evaluer implements Interface{
    private Inscription Id_inscription;
    private Exercices Id_exercices;
    private float Notes;
    private String Commentaire;

    @Override
    public boolean AlreadyExist(Connection connexion) throws SQLException {
        return false;
    }

    @Override
    public void Insertion(Connection connexion) throws SQLException {

    }
}
