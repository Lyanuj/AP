import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Juge {
    private int Id;
    private String Nom, Prenom;
}
