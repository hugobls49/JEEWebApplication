package com.octest.dao;

import java.util.List;

import com.octest.beans.Student;

public interface StudentDao {
	void deleteStudent();
  void ajouterStudents( List<Student>  students );
  List<Student> lister();
	void ajouter( Student student );
	List<Student> getStudentsWithoutTeam();
	List<Student> getStudentsWithoutTeamOrderedByName();
	List<Student> getStudentsByTeam() throws DaoException;
	void  addStudentToTeam(int studentId, int teamId) throws DaoException;
	void removeStudentFromTeam(int studentId) throws DaoException;
}


