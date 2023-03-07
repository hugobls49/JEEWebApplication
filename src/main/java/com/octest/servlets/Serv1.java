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
import com.octest.actions.ChangePage1;
import com.octest.actions.ChangePage2;
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
    }
    
    @Override
    public void init() throws ServletException {
    super.init();
    actionMap.put("0", new ChangePage1());
    actionMap.put("1", new ImportCSV());
    actionMap.put("2", new ChangePage2());
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
        	action.execute(request, response);
        }
        
      
    }

   
}