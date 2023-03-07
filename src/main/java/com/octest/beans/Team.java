package com.octest.beans;

public class Team {
    private int idTeam;
    private String name;
    
    
    public Team() {
    	
    }
    
    
    public Team(int idTeam, String name) {
        this.idTeam = idTeam;
        this.name = name;
    }
    
    // Getters et setters
    public int getIdTeam() {
        return idTeam;
    }
    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
