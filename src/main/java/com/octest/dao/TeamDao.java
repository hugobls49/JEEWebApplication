package com.octest.dao;

import java.util.List;

import com.octest.beans.Team;

public interface TeamDao {
	void ajouterEquipes( int nbEquipes ) throws DaoException ;
	List<Team> getTeams();
	void attribuerEquipes(int nbEquipes) throws DaoException;
	int countEquipes() throws DaoException;
	
}
