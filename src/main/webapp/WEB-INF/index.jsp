<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Serv1</title>
</head>
<body>

	<h1>Ajouter un étudiant</h1>
<form action="Serv1?id=3" method="POST">
    <label for="nom">Nom:</label> 
    <input type="text" id="nom" name="Name" required><br>
    <br> 
    <label for="prenom">Prénom:</label> 
    <input type="text" id="prenom" name="FirstName" required><br>
    <br> 
    <label for="genre">Genre:</label> 
    <input type="radio" id="genre" name="idGender" value="0" required>H 
    <input type="radio" id="genre" name="idGender" value="1" required>F<br>
    <br> 
    <label for="site">Site précédent:</label> 
    <select id="site" name="idSite" required>
        <option value="">--Choisir un site--</option>
        <option value="0">Angers</option>
        <option value="1">Paris</option>
        <option value="2">Dijon</option>
    </select><br>
    <br> 
    <label for="formation">Formation précédente:</label> 
    <select id="formation" name="idFormation" required>
        <option value="">--Choisir une formation--</option>
        <option value="0">P1</option>
        <option value="1">P2</option>
        <option value="2">E3e</option>
        <option value="3">E4e</option>
        <option value="4">E5e</option>
    </select><br>
    <br> 

    <input type="submit" name="ajouter" value="Ajouter">
</form>



	<h1>Importer une liste d'étudiants</h1>
	  	<form action="Serv1?id=1" method="post" enctype="multipart/form-data">
	    <label for="csv-file">Ajouter une liste d'étudiants :</label>
	    <input type="file" id="csv-file" name="csv-file" accept=".csv">
	    <button type="submit">Importer</button>
 	 </form>
 	 
    <h1>Changer de page</h1>
	<form method="POST" action="Serv1?id=0">
		 <button name='buttonPage' type='submit' value='Team'>Team Page</button>
	 </form>
	 
   
    
    
    
</body>
</html>