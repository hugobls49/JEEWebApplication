package com.octest.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.octest.beans.Student;


public class StudentDaoImpl implements StudentDao {
    private DaoFactory daoFactory;

    public StudentDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouter(Student student) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();

            if (connexion != null) {
                preparedStatement = connexion.prepareStatement("INSERT INTO Student(Name, FirstName, idGender, idSite, idFormation, idTeam) VALUES(?, ?, ?, ?, ?, null);");
                if (preparedStatement != null) {
                    preparedStatement.setString(1, student.getName());
                    preparedStatement.setString(2, student.getFirstName());
                    preparedStatement.setInt(3, student.getIdGender());
                    preparedStatement.setInt(4, student.getIdSite());
                    preparedStatement.setInt(5, student.getIdFormation());
                    preparedStatement.executeUpdate();
                } else {
                    throw new SQLException("Failed to create PreparedStatement");
                }
            } else {
                throw new SQLException("Failed to create Connection");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer la connexion et le preparedStatement
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Student> getStudentsWithoutTeam() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "SELECT idStudent, Name, FirstName, idGender, idSite, idFormation FROM Student WHERE idTeam IS NULL");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Student student = new Student();
                student.setIdStudent(resultSet.getInt("idStudent"));
                student.setName(resultSet.getString("Name"));
                student.setFirstName(resultSet.getString("FirstName"));
                student.setIdGender(resultSet.getInt("idGender"));
                student.setIdSite(resultSet.getInt("idSite"));
                student.setIdFormation(resultSet.getInt("idFormation"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

	@Override
	public List<Student> getStudentsByTeam() throws DaoException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<Student> students = new ArrayList<>();
	    
	    try {
	        connection = daoFactory.getConnection();
	        preparedStatement = connection.prepareStatement("SELECT * FROM Student ORDER BY idTeam");
	        resultSet = preparedStatement.executeQuery();
	        
	        while (resultSet.next()) {
	            Student student = new Student();
	            student.setIdStudent(resultSet.getInt("idStudent"));
	            student.setName(resultSet.getString("Name"));
	            student.setFirstName(resultSet.getString("FirstName"));
	            student.setIdGender(resultSet.getInt("idGender"));
	            student.setIdSite(resultSet.getInt("idSite"));
	            student.setIdFormation(resultSet.getInt("idFormation"));
	            student.setIdTeam(resultSet.getInt("idTeam"));
	            students.add(student);
	        }
	    } catch (SQLException e) {
	        throw new DaoException("Erreur lors de la récupération des étudiants par équipe.", e);
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            throw new DaoException("Impossible de fermer la connexion à la base de données", e);
	        }
	    }
	    
	    return students;
	}



}