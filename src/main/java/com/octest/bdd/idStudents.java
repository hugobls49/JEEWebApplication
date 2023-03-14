package com.octest.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class idStudents {

	private Connection connexion;

	public int getIdStudents() {
			
		int idStudents = 0;
        Statement statement = null;
        ResultSet resultat = null;

        loadDatabase();
		
		return 0;
	}
	
    private void loadDatabase() {
        // Chargement du driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }

        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjetJavaEE", "root", "network");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}
