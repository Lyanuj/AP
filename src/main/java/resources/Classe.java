import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Classe {
    private int Id;
    private String Libelle;
    private int Nb_exos;
}
