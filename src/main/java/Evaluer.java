import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * FROM `evaluation` WHERE id_inscription = ? AND id_exercises = ? AND notes = ? AND commentaire = ?");
        preparedStatement.setInt(1, this.Id_inscription.getId());
        preparedStatement.setInt(2, this.Id_exercices.getId());
        preparedStatement.setFloat(3, this.Notes);
        preparedStatement.setString(4, this.Commentaire);

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()){
            return true;
        }
        return false;
    }

    @Override
    public void Insertion(Connection connexion) throws SQLException {
        if (!this.AlreadyExist(connexion)){
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO `evaluation`(`id_inscription`, `id_exercises`, `notes`, `commentaires`) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, this.Id_inscription.getId());
            preparedStatement.setInt(2, this.Id_exercices.getId());
            preparedStatement.setFloat(3, this.Notes);
            preparedStatement.setString(4, this.Commentaire);
            preparedStatement.executeUpdate();
            AlreadyExist(connexion);
        }
    }
}
