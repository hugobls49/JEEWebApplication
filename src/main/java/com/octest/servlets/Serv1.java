package com.octest.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.octest.actions.Action;
import com.octest.actions.ChangePage;
import com.octest.actions.ImportCSV;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Serv1")
public class Serv1 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Map<String,Action> actionMap = new HashMap<>();
       
    public Serv1() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    super.init();
    actionMap.put("0", new ChangePage());
    actionMap.put("1", new ImportCSV());
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
        
        String destination = "";
        
        if(id == null) {
            id = "-1";
        }
        
        Action action = actionMap.get(id);
        
        if(action != null) {
        	destination = action.execute(request, response);
        }
        
      
        request.getRequestDispatcher(destination).forward(request, response);
    }

   
}