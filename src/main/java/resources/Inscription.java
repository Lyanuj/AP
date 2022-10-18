import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Inscription {
    private int Id;
    private Chien Id_chien;
    private Concours Id_concours;
    private Classe Id_classe;
    private Conducteur Id_conducteur;
}
