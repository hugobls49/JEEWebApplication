package com.octest.actions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.octest.beans.Student;
import com.octest.dao.DaoFactory;
import com.octest.dao.StudentDao;
import com.octest.dao.StudentDaoImpl;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class ImportCSV implements Action {

	private StudentDao studentDao;

	public Object execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Part part = request.getPart("csv-file");
		if (part != null) {
			InputStream stream = part.getInputStream();
			List<Student> etudiants = this.importeFichier(stream);
		}

		return response;
	}

	private ArrayList<Student> importeFichier(InputStream fichier) {

		List<Student> etudiants = new ArrayList<Student>();

		try (InputStreamReader filereader = new InputStreamReader(fichier);
				CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();) {

			List<String[]> toutesLesLignes = csvReader.readAll();

			for (String[] ligne : toutesLesLignes) {

				Student etudiant = new Student();

				String input = ligne[0];
				String[] parts = input.split(";");

				etudiant.setIdStudent(0);
				etudiant.setName(parts[1]);
				etudiant.setFirstName(parts[2]);
				etudiant.setIdGender(Integer.parseInt(parts[3]));
				etudiant.setIdSite(Integer.parseInt(parts[4]));
				etudiant.setIdFormation(Integer.parseInt(parts[5]));
				etudiant.setIdTeam(0);

				etudiants.add(etudiant);
			}

			DaoFactory daoFactory = DaoFactory.getInstance();
			this.studentDao = daoFactory.StudentDao();
			this.studentDao.deleteStudent();
			this.studentDao.ajouterStudents(etudiants);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}

		return (ArrayList<Student>) etudiants;
	}

}
