package com.octest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.octest.beans.Student;
import com.octest.beans.Team;

public class TeamDaoImpl implements TeamDao{
	private DaoFactory daoFactory;

	public TeamDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public void ajouterEquipes(int nbEquipes) throws DaoException {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    Statement statement = null;
	    
	    try {
	        connexion = daoFactory.getConnection();
	        // Commencer une transaction
	        connexion.setAutoCommit(false);

	        // Mettre à jour les enregistrements correspondants dans la table Student
	        preparedStatement = connexion.prepareStatement("UPDATE Student SET idTeam = null");
	        preparedStatement.executeUpdate();

	        // Supprimer les équipes déjà présentes
	        statement = connexion.createStatement();
	        statement.executeUpdate("DELETE FROM Team");

	        // Insérer les nouvelles équipes
	        for (int i = 1; i <= nbEquipes; i++) {
	            String equipeName = "Equipe " + i;
	            preparedStatement = connexion.prepareStatement("INSERT INTO Team(idTeam, Name) VALUES(?, ?);");
	            preparedStatement.setInt(1, i);
	            preparedStatement.setString(2, equipeName);
	            preparedStatement.executeUpdate();
	        }

	        // Valider la transaction
	        connexion.commit();
	    } catch (SQLException e) {
	        // En cas d'erreur, annuler la transaction
	        try {
	            if (connexion != null) {
	                connexion.rollback();
	            }
	        } catch (SQLException e2) {
	            throw new DaoException("Impossible d'annuler la transaction", e2);
	        }
	        throw new DaoException("Erreur d'accès à la base de données", e);
	    } finally {
	        // Fermer les ressources utilisées
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (statement != null) {
	                statement.close();
	            }
	            if (connexion != null) {
	                connexion.setAutoCommit(true);
	                connexion.close();
	            }
	        } catch (SQLException e) {
	            throw new DaoException("Impossible de fermer la connexion à la base de données", e);
	        }
	    }
	}



	@Override
	public List<Team> getTeams() {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<Team> teams = new ArrayList<Team>();

	    try {
	        connection = daoFactory.getConnection();
	        preparedStatement = connection.prepareStatement("SELECT * FROM Team");
	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            Team team = new Team();
	            team.setIdTeam(resultSet.getInt("idTeam"));
	            team.setName(resultSet.getString("Name"));
	            teams.add(team);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return teams;
	}


	@Override
	public void attribuerEquipes(int nbEquipes) throws DaoException {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    Statement statement = null;
	    
	    try {
	        connexion = daoFactory.getConnection();
	        // Commencer une transaction
	        connexion.setAutoCommit(false);
	        
	        
	        // Affecter les étudiants à des équipes aléatoires
	        StudentDaoImpl studentDaoImpl = new StudentDaoImpl(daoFactory);
	        List<Student> studentsWithoutTeam = studentDaoImpl.getStudentsWithoutTeam();
	        Collections.shuffle(studentsWithoutTeam);
	        int studentsPerTeam = studentsWithoutTeam.size() / nbEquipes;
	        
	        if (studentsWithoutTeam.size() % nbEquipes != 0) {
	            throw new DaoException("Le nombre d'étudiants ne permet pas une répartition égale dans les équipes.");
	        }
	        
	        int currentTeam = 1;
	        int studentCount = 0;
	        for (Student student : studentsWithoutTeam) {
	            if (studentCount == studentsPerTeam) {
	                // Passer à l'équipe suivante
	                currentTeam++;
	                studentCount = 0;
	            }
	            preparedStatement = connexion.prepareStatement("UPDATE Student SET idTeam=? WHERE idStudent=?");
	            preparedStatement.setInt(1, currentTeam);
	            preparedStatement.setInt(2, student.getIdStudent());
	            preparedStatement.executeUpdate();
	            student.setIdTeam(currentTeam);
	            studentCount++;
	        }
	        
	        // Valider la transaction
	        connexion.commit();
	    } catch (SQLException e) {
	        try {
	            if (connexion != null) {
	                connexion.rollback();
	            }
	        } catch (SQLException e2) {
	            throw new DaoException("Impossible de rollback", e2);
	        }
	        throw new DaoException("Impossible de communiquer avec la base de données", e);
	    } finally {
	        // Fermer la connexion et les statements
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (statement != null) {
	                statement.close();
	            }
	            if (connexion != null) {
	                connexion.close();
	            }
	        } catch (SQLException e) {
	            throw new DaoException("Impossible de fermer la connexion à la base de données", e);
	        }
	    }
	}



	    @Override
	    public int countEquipes() throws DaoException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        int nbEquipes = 0;
	        
	        try {
	            connection = daoFactory.getConnection();
	            preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS nbEquipes FROM Team");
	            resultSet = preparedStatement.executeQuery();
	            
	            if (resultSet.next()) {
	                nbEquipes = resultSet.getInt("nbEquipes");
	            }
	        } catch (SQLException e) {
	            throw new DaoException("Erreur lors du comptage des équipes.", e);
	        } 
	        
	        return nbEquipes;
	    }
	    
	    
}





	

