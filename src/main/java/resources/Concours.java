import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
@Builder
public class Concours {
    private int id;
    private Juge Id_Juge;
    private Club Id_Club;
    private String Nom;
    private Date Debut_Date, Fin_Date;
}
