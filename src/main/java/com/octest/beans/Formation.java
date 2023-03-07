package com.octest.beans;

public class Formation {
    private int idFormation;
    private String name;
   
    public Formation() {
    	
    }
    
    
    public Formation(int idFormation, String name) {
        this.idFormation = idFormation;
        this.name = name;
    }
    
    // Getters et setters
    public int getIdFormation() {
        return idFormation;
    }
    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
