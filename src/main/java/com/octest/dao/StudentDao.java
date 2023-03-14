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
	List<Student> getStudentsByTeam() throws DaoException;
  
}