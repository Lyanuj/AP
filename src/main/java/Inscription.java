import com.mysql.cj.x.protobuf.MysqlxPrepare;
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
public class Inscription implements Interface{
    private int Id;
    private Chien Id_chien;
    private Concours Id_concours;
    private Classe Id_classe;
    private Conducteur Id_conducteur;

    @Override
    public boolean AlreadyExist(Connection connexion) throws SQLException {
        PreparedStatement sql = connexion.prepareStatement("SELECT FROM `inscription` INNER JOIN concours ON concours.id = inscription.id_concours INNER JOIN classe ON classe.id = inscription.id_classe INNER JOIN chien ON chien.fapac = inscription.id_chien INNER JOIN conducteur ON conducteur.id = inscription.id_conducteur WHERE inscription.id_concours = ? AND inscription.id_classe = ? AND inscription.id_chien = ? AND inscription.id_conducteur = ?");
        sql.setInt(1,this.Id_concours.getId());
        sql.setInt(2,this.Id_classe.getId());
        sql.setInt(3,this.Id_chien.getFapac());
        sql.setInt(4,this.Id_conducteur.getId());

        ResultSet res = sql.executeQuery();
        if (res.next()){
            this.Id = res.getInt("id");
            return true;
        }
        return false;
    }

    @Override
    public void Insertion(Connection connexion) throws SQLException {
        if (!this.AlreadyExist(connexion)){
            PreparedStatement sql = connexion.prepareStatement("INSERT INTO `inscription`(`concours`, `classe`, `chien`, `conducteur`) VALUES (?,?,?,?)");
            sql.setInt(1,this.Id_concours.getId());
            sql.setInt(2,this.Id_classe.getId());
            sql.setInt(3,this.Id_chien.getFapac());
            sql.setInt(4,this.Id_conducteur.getId());
            System.out.println(sql);
            sql.executeUpdate();
            this.AlreadyExist(connexion);
        }
    }
}
