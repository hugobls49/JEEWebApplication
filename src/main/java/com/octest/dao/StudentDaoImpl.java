package com.octest.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.octest.beans.Student;

public class StudentDaoImpl implements StudentDao {
    private DaoFactory daoFactory;

    StudentDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouterStudents( List<Student>  students ) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        
		for(int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i).getString());
		}

        try {
            connexion = daoFactory.getConnection();
            for(int i = 0; i < students.size(); i++) {
                preparedStatement = connexion.prepareStatement("ALTER TABLE Student AUTO_INCREMENT = 1;");
                preparedStatement.executeUpdate();
                preparedStatement = connexion.prepareStatement("INSERT INTO Student (Name, FirstName, idGender, idSite, idFormation, idTeam) VALUES (?, ?, ?, ?, ?, null);");
                preparedStatement.setString(1, students.get(i).getName());
                preparedStatement.setString(2, students.get(i).getFirstName());
                preparedStatement.setInt(3, students.get(i).getIdGender());
                preparedStatement.setInt(4, students.get(i).getIdSite());
                preparedStatement.setInt(5, students.get(i).getIdFormation());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
	@Override
	public void deleteStudent() {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM Student;");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }		
	}

	@Override
public List<Student> lister() {
		return null;
}

@Override
public void ajouter(Student student) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();

            if (connexion != null) {
                preparedStatement = connexion.prepareStatement("INSERT INTO Student(Name, FirstName, idGender, idSite, idFormation, idTeam) VALUES(?, ?, ?, ?, ?, ?);");
                if (preparedStatement != null) {
                    preparedStatement.setString(1, student.getName());
                    preparedStatement.setString(2, student.getFirstName());
                    preparedStatement.setInt(3, student.getIdGender());
                    preparedStatement.setInt(4, student.getIdSite());
                    preparedStatement.setInt(5, student.getIdFormation());
                    preparedStatement.setInt(6, student.getIdTeam());
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
}