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
public class Exercices implements Interface{
    private int Id;
    private Classe Id_classe;
    private int Coef;
    private String Libelle;

    @Override
    public boolean AlreadyExist(Connection connexion) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * FROM `exercise` WHERE id_classe = ? AND libelle = ? AND coef = ?");
        preparedStatement.setInt(1, this.Id_classe.getId());
        preparedStatement.setString(2, this.Libelle);
        preparedStatement.setInt(3, this.Coef);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()){
            this.Id = rs.getInt("id");
            return true;
        }
        return false;
    }

    @Override
    public void Insertion(Connection connexion) throws SQLException {
        if (!this.AlreadyExist(connexion)) {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO `exercise`(`id_classe`, `libelle`, `coef`) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, this.Id_classe.getId());
            preparedStatement.setString(2, this.Libelle);
            preparedStatement.setInt(3, this.Coef);
            preparedStatement.executeUpdate();
            AlreadyExist(connexion);
        }
    }
}
