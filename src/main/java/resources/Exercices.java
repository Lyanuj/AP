import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Exercices {
    private int Id;
    private Classe Id_classe;
    private int Coef;
    private String Libelle;

}
