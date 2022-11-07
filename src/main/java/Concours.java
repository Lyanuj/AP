import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * FROM `contest` WHERE id_juge = ? AND id_club = ?");
        preparedStatement.setInt(1, this.Id_Juge.getId());
        preparedStatement.setInt(2, this.Id_Club.getId());
        ResultSet rs=preparedStatement.executeQuery();
        if(rs.next()){
            this.id = rs.getInt("id");
            return true;
        }
        return false;
    }

    @Override
    public void Insertion(Connection connexion) throws SQLException {
        if(!this.AlreadyExist(connexion)) {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO `contest` (`id_juge`, `id_club`, `nom`, `Debut_Date`, `Fin_Date`) VALUES (?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, this.Id_Juge.getId());
            preparedStatement.setInt(2, this.Id_Club.getId());
            preparedStatement.setString(3, this.Nom);
            preparedStatement.setDate(4, new java.sql.Date(this.Debut_Date.getTime()));
            preparedStatement.setDate(5, new java.sql.Date(this.Fin_Date.getTime()));
            preparedStatement.executeUpdate();
            this.AlreadyExist(connexion);
        }
    }
}
