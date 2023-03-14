package com.octest.dao;

import java.util.List;

import com.octest.beans.Student;

public interface StudentDao {
	void ajouter( Student student );
	List<Student> getStudentsWithoutTeam();
	List<Student> getStudentsByTeam() throws DaoException;
}

