import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Evaluer {
    private Inscription Id_inscription;
    private Exercices Id_exercices;
    private float Notes;
    private String Commentaire;
}
