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
public class Juge implements Interface{
    private int Id;
    private String Nom, Prenom;

    @Override
    public boolean AlreadyExist(Connection connexion) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * FROM `judge` WHERE prenom = ? AND nom = ? ");
        preparedStatement.setString(1, this.Prenom);
        preparedStatement.setString(2, this.Nom);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            this.Id = rs.getInt("id");
            return true;
        }
        return false;
    }

    @Override
    public void Insertion(Connection connexion) throws SQLException {
        if(!this.AlreadyExist(connexion)) {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO `judge` (`prenom`, `nom`) VALUES (?, ?);");
            preparedStatement.setString(1, this.Prenom);
            preparedStatement.setString(2, this.Nom);
            preparedStatement.executeUpdate();
            this.AlreadyExist(connexion);
        }
    }
}
