import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Club {
    private int Id;
    private String Nom, Adresse, Ville, Cp;

}
