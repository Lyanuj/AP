import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Connection;
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
                return false;
        }

        @Override
        public void Insertion(Connection connexion) throws SQLException {

        }
}
