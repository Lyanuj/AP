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
public class Club implements Interface{
    private int Id;
    private String Nom, Adresse, Ville, Cp;

    @Override
    public boolean AlreadyExist(Connection connexion) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * FROM `club` WHERE nom = ?");
        preparedStatement.setString(1, this.Nom);
        ResultSet rs=preparedStatement.executeQuery();
        if(rs.next()){
            this.Id = rs.getInt("id");
            return true;
        }
        return false;
    }

    @Override
    public void Insertion(Connection connexion) throws SQLException {
        if (!this.AlreadyExist(connexion)) {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO `club` (`nom`, `adresse`, `cp`, `ville`) VALUES (?, ?, ?, ?);");
            preparedStatement.setString(1, this.Nom);
            preparedStatement.setString(2, this.Adresse);
            preparedStatement.setString(3, this.Cp);
            preparedStatement.setString(4, this.Ville);
            preparedStatement.executeUpdate();
            this.AlreadyExist(connexion);
        }
    }
}
