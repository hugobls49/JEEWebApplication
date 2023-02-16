package com.octest.beans;

public class Etudiant {
	  private String nom;
	  private String prenom;
	  private String genre;
	  private String sitePrecedent;
	  private String formationPrecedente;

	  public Etudiant() {
	  }

	  public Etudiant(String nom, String prenom, String genre, String sitePrecedent, String formationPrecedente) {
	    this.nom = nom;
	    this.prenom = prenom;
	    this.genre = genre;
	    this.sitePrecedent = sitePrecedent;
	    this.formationPrecedente = formationPrecedente;
	  }

	  public String getNom() {
	    return nom;
	  }

	  public void setNom(String nom) {
	    this.nom = nom;
	  }

	  public String getPrenom() {
	    return prenom;
	  }

	  public void setPrenom(String prenom) {
	    this.prenom = prenom;
	  }

	  public String getGenre() {
	    return genre;
	  }

	  public void setGenre(String genre) {
	    this.genre = genre;
	  }

	  public String getSitePrecedent() {
	    return sitePrecedent;
	  }

	  public void setSitePrecedent(String sitePrecedent) {
	    this.sitePrecedent = sitePrecedent;
	  }

	  public String getFormationPrecedente() {
	    return formationPrecedente;
	  }

	  public void setFormationPrecedente(String formationPrecedente) {
	    this.formationPrecedente = formationPrecedente;
	  }
	}
