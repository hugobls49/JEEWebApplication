<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Serv1</title>
</head>
<body>

	<h1>Créer des étudiants</h1>
    <form action="Serv1?id=2">
      <label for="nom">Nom:</label>
      <input type="text" id="nom" name="nom" required><br><br>
      <label for="prenom">Prénom:</label>
      <input type="text" id="prenom" name="prenom" required><br><br>
      <label for="genre">Genre:</label>
      <input type="radio" id="genre" name="genre" value="f" required>Femme
      <input type="radio" id="genre" name="genre" value="h" required>Homme<br><br>
      <label for="site">Site précédent:</label>
      <input type="text" id="site" name="site" required><br><br>
      <label for="formation">Formation précédente:</label>
      <input type="text" id="formation" name="formation" required><br><br>
      <input type="submit" value="Ajouter">
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