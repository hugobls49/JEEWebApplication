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

//    @Override
//    public List<Student> lister() {
//        List<Student> students = new ArrayList<Student>();
//        Connection connexion = null;
//        Statement statement = null;
//        ResultSet resultat = null;
//
//        try {
//            connexion = daoFactory.getConnection();
//            statement = connexion.createStatement();
//            resultat = statement.executeQuery("SELECT Name, FirstName, idGender, idSite, idFormation, idTeam FROM Student;");
//
//            while (resultat.next()) {
//                String nom = resultat.getString("Name");
//                String prenom = resultat.getString("Firstname");
//                int sex = resultat.getInt("idGender");
//                int site =resultat.getInt("idSite");
//                int formation= resultat.getInt("idFormation");
//                int equipe = resultat.getInt("idTeam");
//                
//                Student student = new Student();
//                student.setName(nom);
//                student.setFirstName(prenom);
//                student.setIdGender(sex);
//                student.setIdSite(site);
//                student.setIdFormation(formation);
//                student.setIdTeam(equipe);
//                
//                students.add(student);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return students;
//    }

}