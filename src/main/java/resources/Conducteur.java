import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Conducteur {
    private int Id;
    private String Nom, Prenom;
}
