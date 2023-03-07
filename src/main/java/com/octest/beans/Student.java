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
}