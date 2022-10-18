import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
@Builder
public class Chien {
        private int Fapac;
        private Club Id_club;
        private String Nom, Race;
        private String Sexe, Homologue;
        private Date DateNaissance;

}
