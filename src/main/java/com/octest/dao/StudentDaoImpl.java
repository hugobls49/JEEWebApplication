package com.octest.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;

import com.octest.beans.Student;

public class StudentDaoImpl implements StudentDao {
    private DaoFactory daoFactory;

    public StudentDaoImpl(DaoFactory daoFactory) {
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
            
            statement.close();
            connection.close();
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
	public void addStudentToTeam(int studentId, int teamId) throws DaoException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    try {
	        connection = daoFactory.getConnection();
	        preparedStatement = connection.prepareStatement("UPDATE Student SET idTeam = ? WHERE idStudent = ? AND idTeam IS NULL");
	        preparedStatement.setInt(1, teamId);
	        preparedStatement.setInt(2, studentId);
	        preparedStatement.executeUpdate();
	        
	        // Retirer le Student de la liste getStudentWithoutTeam
	        List<Student> students = getStudentsWithoutTeam();
	        for (Iterator<Student> iterator = students.iterator(); iterator.hasNext();) {
	            Student student = iterator.next();
	            if (student.getIdStudent() == studentId) {
	                iterator.remove();
	                break;
	            }
	        }
	    } catch (SQLException e) {
	        throw new DaoException("Erreur lors de l'ajout de l'étudiant à l'équipe.", e);
	    } finally {
	        try {
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
	}

	@Override
	public void removeStudentFromTeam(int studentId) throws DaoException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    try {
	        connection = daoFactory.getConnection();
	        preparedStatement = connection.prepareStatement("UPDATE Student SET idTeam = null WHERE idStudent = ?");
	        preparedStatement.setInt(1, studentId);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        throw new DaoException("Erreur lors du retrait de l'étudiant de l'équipe.", e);
	    } finally {
	        try {
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
	}

	@Override
	public List<Student> getStudentsWithoutTeamOrderedByName() {
		 List<Student> students = new ArrayList<>();
	        try (Connection connection = daoFactory.getConnection();
	             PreparedStatement statement = connection.prepareStatement(
	                "SELECT idStudent, Name, FirstName, idGender, idSite, idFormation FROM Student WHERE idTeam IS NULL ORDER BY Name ASC, FirstName ASC");
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
	            
	            statement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return students;
	    }
	

@Override
public ResultSet  selectionTousLesEtudiants() {
	Connection connexion = null;
	
    String query = "SELECT * FROM Student";
	// Exécuter l'instruction SQL et obtenir un ResultSet
    try {
		connexion = daoFactory.getConnection();
	    Statement stmt = connexion.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
		return rs;	
	} catch (SQLException e) {
		e.printStackTrace();
	}
    return null;
}

}