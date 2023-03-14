package com.octest.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.dao.DaoException;
import com.octest.dao.DaoFactory;
import com.octest.dao.TeamDao;

public class ComposerEquipeOrdreAlphabétique implements Action{
	
	private TeamDao teamDao;
	@Override
	public Object execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DaoFactory daoFactory = DaoFactory.getInstance();
	    this.teamDao = daoFactory.TeamDao();
	    
	    int nbEquipes = 0;
	    try {
	        nbEquipes = teamDao.countEquipes();
	        teamDao.attribuerEquipesParOdreAlphbétique(nbEquipes);
	    } catch (DaoException e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", e.getMessage());
	    }

	    return response;
	}

}
