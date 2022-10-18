<?php
    /*
    $note=;
    $coeff=;
    $pointsTotaux = ;
    $classement =;
    $nomChien;
    $nomMaitre;*/
    $dbh = new PDO('mysql:host=localhost; dbname=ap; charset=utf8', 'root', '');
    $stmt = $dbh->prepare("SELECT chien.nom, conducteur.nom, conducteur.prenom FROM conducteur INNER JOIN inscription ON conducteur.id =  inscritpion.id_conducteur INNER JOIN chien ON inscription.id_chien = chien.fapac ");
    $stmt->execute();
    foreach ($stmt as $row) {
        echo $row['chien.nom']."<br>";


    }
?>