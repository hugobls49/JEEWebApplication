package com.octest.dao;

import java.sql.ResultSet;
import java.util.List;

import com.octest.beans.Student;

public interface StudentDao {

	void deleteStudent();
	void ajouterStudents(List<Student> students);
	ResultSet selectionTousLesEtudiants();
	void ajouter(Student student);
	List<Student> lister();
	List<Student> getStudentsWithoutTeam();
	List<Student> getStudentsWithoutTeamOrderedByName();
	List<Student> getStudentsByTeam() throws DaoException;

	void  addStudentToTeam(int studentId, int teamId) throws DaoException;
	void removeStudentFromTeam(int studentId) throws DaoException;
}

