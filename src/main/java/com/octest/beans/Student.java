package com.octest.beans;

public class Student {
    private int idStudent;
    private String name;
    private String firstName;
    private int idGender;
    private int idSite;
    private int idFormation;
    private int idTeam;
    
    public Student() {
    	
    }
    
    public Student(int idStudent, String name, String firstName, int idGender, int idSite, int idFormation, int idTeam) {
        this.idStudent = idStudent;
        this.name = name;
        this.firstName = firstName;
        this.idGender = idGender;
        this.idSite = idSite;
        this.idFormation = idFormation;
        this.idTeam = idTeam;
    }
    
  
    public int getIdStudent() {
        return idStudent;
    }
    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public int getIdGender() {
        return idGender;
    }
    public void setIdGender(int idGender) {
        this.idGender = idGender;
    }
    public int getIdSite() {
        return idSite;
    }
    public void setIdSite(int idSite) {
        this.idSite = idSite;
    }
    public int getIdFormation() {
        return idFormation;
    }
    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }
    public int getIdTeam() {
        return idTeam;
    }
    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }
    
    public String getString() {
    	
    	String genre;
    	String site;
    	String formation;
    	
    	if(this.idGender == 0) {
    		genre = "homme";
    	}
    	else {
    		genre = "femme";
    	}
    	
    	if(this.idSite == 0) {
    		site = "Angers";
    	}else if(this.idSite == 1){
    		site = "Paris";
    	}else {
    		site = "Dijon";
    	}
    	
    	if(this.idSite == 0) {
    		formation = "P1";
    	}else if(this.idSite == 1){
    		formation = "P2";
    	}else if(this.idSite == 2){
    		formation = "E3e";
    	}else if(this.idSite == 3){
    		formation = "E4e";
    	}else{
    		formation = "E5e";
    	}
    	
		return this.getFirstName() + " " + this.getName() + " est un " + genre + " à " + site + " en " + formation;
    	
    }
}