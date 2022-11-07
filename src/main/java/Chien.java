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
public class Chien implements Interface{
        private int Fapac;
        private Club Id_club;
        private String Nom, Race;
        private String Sexe, Homologue;
        private Date DateNaissance;

        @Override
        public boolean AlreadyExist(Connection connexion) throws SQLException {
                PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * FROM `dog` WHERE FAPAC = ? AND nom = ? ");
                preparedStatement.setInt(1, this.Fapac);
                preparedStatement.setString(2, this.Nom);
                ResultSet rs=preparedStatement.executeQuery();
                if(rs.next()){
                        return true;
                }
                return false;
        }

        @Override
        public void Insertion(Connection connexion) throws SQLException {
                if (!this.AlreadyExist(connexion)) {
                        PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO `dog` (`FAPAC`, `id_club`, `nom`, `race`, `dateNaissance`, `sexe`) VALUES (?, ?, ?, ?, ?, ?);");
                        preparedStatement.setInt(1, this.Fapac);
                        preparedStatement.setInt(2, this.Id_club.getId());
                        preparedStatement.setString(3, this.Nom);
                        preparedStatement.setString(4, this.Race);
                        preparedStatement.setDate(5, new java.sql.Date(this.DateNaissance.getTime()));
                        preparedStatement.setString(6, String.valueOf(this.Sexe));
                        preparedStatement.executeUpdate();
                        this.AlreadyExist(connexion);
                }
        }
}
