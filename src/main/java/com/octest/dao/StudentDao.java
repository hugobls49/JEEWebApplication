package com.octest.dao;

import java.util.List;

import com.octest.beans.Student;

public interface StudentDao {
	void deleteStudent();
    void ajouterStudents( List<Student>  students );
    List<Student> lister();
}