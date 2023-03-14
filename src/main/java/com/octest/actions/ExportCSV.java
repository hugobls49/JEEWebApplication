package com.octest.actions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.dao.DaoFactory;
import com.octest.dao.StudentDao;

public class ExportCSV implements Action {

	private StudentDao studentDao;
	private ResultSet rs;

	@Override
	public Object execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"etudiants.csv\"");

		File file = File.createTempFile("etudiants", ".csv");
		FileWriter fw = new FileWriter(file);

		DaoFactory daoFactory = DaoFactory.getInstance();
		this.studentDao = daoFactory.StudentDao();
		rs = this.studentDao.selectionTousLesEtudiants();

		try {

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			// écrire les noms des colonnes dans le fichier CSV
			for (int i = 1; i <= columnsNumber; i++) {
				fw.write(rsmd.getColumnName(i));
				if (i != columnsNumber) {
					fw.write(";");
				}
			}

			fw.write("\n");
			// écrire les données de la table dans le fichier CSV
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					String columnValue = rs.getString(i);
					if (columnValue != null) {
						fw.write(columnValue);
					}
					if (i != columnsNumber) {
						fw.write(";");
					}
				}
				fw.write("\n");
			}
			fw.close();

			PrintWriter out = response.getWriter();
			out.print(new String(java.nio.file.Files.readAllBytes(file.toPath())));
			out.flush();
			out.close();

		} catch (Exception e) {
			System.err.println("Erreur : " + e.getMessage());
		}
		return response;
	}

}
