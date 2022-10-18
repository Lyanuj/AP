import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;



public class Principale {

    public static void main(String[] args) throws IOException, ParseException {

        //read
        String excelFilePath = "appCanin.xlsx";

        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        XSSFCell cell = sheet.getRow(3).getCell(4);
        String classe = sheet.getRow(0).getCell(21).toString();
        String nomDuChien = cell.toString();
        String fapac = sheet.getRow(3).getCell(31).toString();
        String nomConducteur = sheet.getRow(8).getCell(6).toString();
        String licence = sheet.getRow(8).getCell(31).toString();
        String race = sheet.getRow(4).getCell(3).toString();
        String nomClub = sheet.getRow(5).getCell(5).toString();
        String dates = sheet.getRow(3).getCell(36).toString();
        String sexe = sheet.getRow(4).getCell(16).toString();
        String dateNaissance = sheet.getRow(4).getCell(19).toString();
        String nomJuge = sheet.getRow(35).getCell(0).toString();
        String homologue = sheet.getRow(0).getCell(26).toString();
        String typeConcours = sheet.getRow(0).getCell(5).toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        Date naissance = dateFormat.parse(dateNaissance);
        String dateDebut = dates.split(" ")[0];
        String dateFin = dates.split(" ")[2];
        DateFormat dateFormatDebut = new SimpleDateFormat("yyyy/mm/dd");
        DateFormat dateFormatFin = new SimpleDateFormat("yyyy/mm/dd");
        Date dateDebut1 = dateFormatDebut.parse(dateDebut);
        Date dateFin1 = dateFormatFin.parse(dateFin);
        String prenomJuge = nomJuge.split(" ")[1];
        nomJuge = nomJuge.split(" ")[0];
        fapac = fapac.split("\\.")[0];
        String prenomConducteur = nomConducteur.split(" ")[1];
        nomConducteur = nomConducteur.split(" ")[2];

        // Instanciation des objets
        Club club = Club.builder().Nom(nomClub).build();
        Chien chien = Chien.builder().Fapac(Integer.parseInt(fapac)).Sexe(sexe).DateNaissance(naissance).Nom(nomDuChien).Race(race).build();
        Juge juge = Juge.builder().Nom(nomJuge).Prenom(prenomJuge).build();
        Concours concours = Concours.builder().Nom(typeConcours).Debut_Date(dateDebut1).Fin_Date(dateFin1).build();
        Conducteur conducteur = Conducteur.builder().Nom(nomConducteur).Prenom(prenomConducteur).build();
        Inscription inscription = Inscription.builder().build();
        Classe classe1 = Classe.builder().build();



        if( classe.equals("Classe 1")){
            for (int i = 0; i < 9; i++){
                String exo = sheet.getRow(14+i).getCell(0).toString();
                float note = Float.parseFloat(sheet.getRow(14+i).getCell(12).toString().replace(',', '.'));
                String coeff = sheet.getRow(14+i).getCell(15).toString();
                String commentaire = sheet.getRow(14+i).getCell(21).toString();

                coeff = coeff.split("\\.")[0];

                Exercices exercices = Exercices.builder().Libelle(exo).Coef(Integer.parseInt(coeff)).build();
                Evaluer evaluer = Evaluer.builder().Notes(note).Commentaire(commentaire).build();
                System.out.println("Exercice: " + exo + " obtient un " + note + " coefficient "+ coeff+ "(" + commentaire +")");
            }
        }

        workbook.close();
        inputStream.close();

    }
}

