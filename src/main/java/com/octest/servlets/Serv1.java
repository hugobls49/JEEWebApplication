package com.octest.servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.octest.actions.Action;
import com.octest.actions.ActualiserStudentWithoutTeam;
import com.octest.actions.AfficherEtudiantParEquipe;
import com.octest.actions.AjouterEtudiant;
import com.octest.actions.ChangePage1;
import com.octest.actions.ChangePage2;
import com.octest.actions.ComposerEquipes;
import com.octest.actions.ImportCSV;
import com.octest.actions.selectNbTeam;
import com.octest.beans.Student;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;


import com.octest.dao.DaoFactory;


/**
 * Servlet implementation class Test
 */
@MultipartConfig(
	    fileSizeThreshold = 1048576, // 1 Mo
	    maxFileSize = 10485760, // 10 Mo
	    maxRequestSize = 52428800 // 5 x 10 Mo
	)
@WebServlet("/Serv1")
public class Serv1 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Map<String,Action> actionMap = new HashMap<>();
       
    public Serv1() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    
    super.init();
    actionMap.put("0", new ChangePage1());
    actionMap.put("1", new ImportCSV());
    actionMap.put("2", new ChangePage2());
    actionMap.put("3", new AjouterEtudiant());
    actionMap.put("4", new selectNbTeam());
    actionMap.put("5", new ActualiserStudentWithoutTeam());
    actionMap.put("6", new ComposerEquipes());
    actionMap.put("7", new AfficherEtudiantParEquipe());
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

    }
    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	processRequest(request,response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        Action action = actionMap.get(id);
        
        if(id == null) {
            id = "-1";
        }

        if(action != null) {
        	response = (HttpServletResponse) action.execute(request, response);
        	if (action instanceof AjouterEtudiant) {
                request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
            }
            
          if (action instanceof ImportCSV ) {
                request.setAttribute("fichierEnvoye", true);
                request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
            }
            
        	if (action instanceof selectNbTeam || action instanceof ActualiserStudentWithoutTeam || action instanceof ComposerEquipes || action instanceof AfficherEtudiantParEquipe) {
                request.getRequestDispatcher("/WEB-INF/team.jsp").forward(request, response);
            }
            
        		
        	
        }

    }
}