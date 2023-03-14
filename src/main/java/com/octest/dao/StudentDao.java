package com.octest.dao;

import java.util.List;

import com.octest.beans.Student;

public interface StudentDao {
	void deleteStudent();
  void ajouterStudents( List<Student>  students );
  List<Student> lister();
	void ajouter( Student student );
	List<Student> getStudentsWithoutTeam();
	List<Student> getStudentsByTeam() throws DaoException;

}


