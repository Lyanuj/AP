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
public class Classe implements Interface{
    private int Id;
    private String Libelle;
    private int Nb_exos;

    @Override
    public boolean AlreadyExist(Connection connexion) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * FROM classe WHERE libelle = ?");
        preparedStatement.setString(1, this.Libelle);
        ResultSet rs= preparedStatement.executeQuery();
        if(rs.next()){
            this.Id = rs.getInt("id");
            return true;
        }
        return false;
    }

    @Override
    public void Insertion(Connection connexion) throws SQLException {
        if (!this.AlreadyExist(connexion)) {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO `classe` (`libelle`, `Nb_exo`) VALUES (?, ?)");
            preparedStatement.setString(1, this.Libelle);
            preparedStatement.setInt(2, this.Nb_exos);
            preparedStatement.executeUpdate();
            this.AlreadyExist(connexion);
        }
    }
}
