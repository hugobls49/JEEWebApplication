package com.octest.actions;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.beans.Etudiant;
import com.opencsv.CSVReader;

public class ImportCSV implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        String cheminFichier = "\\I2\\LD\\JEE\\Projet\\CSV_FILES\\test.csv";
        List<Etudiant> etudiants = new ArrayList<Etudiant>();
        
        try {
            CSVReader csvReader = new CSVReader(new FileReader(cheminFichier));
           
            String[] ligne;
            while ((ligne = csvReader.readNext()) != null) {
                String nom = ligne[0];
                String prenom = ligne[1];
                String genre = ligne[2];
                String site = ligne[3];
                String formation = ligne[4];
                Etudiant etudiant = new Etudiant(nom, prenom, genre, site, formation);
                etudiants.add(etudiant);
            }
            csvReader.close();
            // Ajouter cette liste d'étudiant dans la base de données 
        } catch (Exception e) {
            // Gérer les erreurs
        }
		return null;
	}

}
