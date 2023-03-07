package com.octest.beans;

public class Site {
    private int idSite;
    private String name;
    

    public Site() {
    	
    }

    public Site(int idSite, String name) {
    	this.idSite = idSite;
        this.name=name;
    }
    
	public int getIdSite() {
		return idSite;
	}

	public void setIdSite(int idSite) {
		this.idSite = idSite;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
        