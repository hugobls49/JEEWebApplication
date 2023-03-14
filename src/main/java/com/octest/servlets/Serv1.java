package com.octest.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.actions.Action;

import com.octest.actions.ActualiserStudentWithoutTeam;
import com.octest.actions.ActualiserStudentWithoutTeamAndTeam;
import com.octest.actions.AfficherEtudiantParEquipe;
import com.octest.actions.AjouterEtudiant;
import com.octest.actions.AjouterEtudiantEquipe;
import com.octest.actions.ChangePage1;
import com.octest.actions.ChangePage2;
import com.octest.actions.ComposerEquipeOrdreAlphabétique;
import com.octest.actions.ComposerEquipes;
import com.octest.actions.ImportCSV;

import com.octest.actions.ModifierNomEquipe;
import com.octest.actions.RetirerEtudiantEquipe;

import com.octest.actions.ExportCSV;

import com.octest.actions.selectNbTeam;


/**
 * Servlet implementation class Test
 */
@MultipartConfig(fileSizeThreshold = 1048576, // 1 Mo
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
    actionMap.put("8", new AjouterEtudiantEquipe());
    actionMap.put("9", new ActualiserStudentWithoutTeamAndTeam());
    actionMap.put("10", new RetirerEtudiantEquipe());
    actionMap.put("11", new ExportCSV());
    actionMap.put("12", new ModifierNomEquipe());
    actionMap.put("13", new ComposerEquipeOrdreAlphabétique());

  
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
        	if (action instanceof AjouterEtudiant || action instanceof ImportCSV) {
                request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
            }
            
        	if (action instanceof selectNbTeam || action instanceof ActualiserStudentWithoutTeam 
        			|| action instanceof ComposerEquipes || action instanceof AfficherEtudiantParEquipe
        			|| action instanceof AjouterEtudiantEquipe || action instanceof ActualiserStudentWithoutTeamAndTeam
        			|| action instanceof RetirerEtudiantEquipe || action instanceof ModifierNomEquipe
        			|| action instanceof ComposerEquipeOrdreAlphabétique) {
                request.getRequestDispatcher("/WEB-INF/team.jsp").forward(request, response);
            }
            
          if (action instanceof ExportCSV) {
            request.getRequestDispatcher("/WEB-INF/team.jsp").forward(request, response);
          }
        	
        }

    }
}