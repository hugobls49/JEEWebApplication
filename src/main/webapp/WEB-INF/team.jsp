<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team Making</title>
</head>
<body>
	<body>
	<h1>Gestion des équipes</h1>

	<!-- Choisir le nombre d'équipes devant être créées -->
	<label for="nb-equipes">Nombre d'équipes :</label>
	<input type="number" id="nb-equipes" name="nb-equipes" min="1" required>
	<button id="btn-creer-equipes">Créer les équipes</button>

	<!-- Afficher la liste des étudiants sans équipe -->
	<h2>Liste des étudiants sans équipe</h2>
	<table>
		<thead>
			<tr>
				<th>Nom</th>
				<th>Prénom</th>
				<th>Genre</th>
				<th>Site précédent</th>
				<th>Formation précédente</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody id="tbl-etudiants-sans-equipe">
			<!-- Les étudiants sans équipe seront ajoutés dynamiquement ici -->
		</tbody>
	</table>

	<!-- Afficher les équipes constituées -->
	<h2>Equipes constituées</h2>
	<div id="div-equipes">
		<!-- Les équipes seront ajoutées dynamiquement ici -->
	</div>

	<!-- Proposer une composition automatique des équipes -->
	<h2>Composition automatique des équipes</h2>
	<button id="btn-composer-equipes">Composer les équipes automatiquement</button>

	<!-- Modifier la composition d'une équipe -->
	<h2>Modifier une équipe</h2>
	<label for="select-equipe">Choisir une équipe :</label>
	<select id="select-equipe" name="select-equipe">
		<!-- Les options avec les noms des équipes seront ajoutées dynamiquement ici -->
	</select>
	<label for="select-etudiant">Ajouter ou supprimer un étudiant :</label>
	<select id="select-etudiant" name="select-etudiant">
		<!-- Les options avec les étudiants sans équipe seront ajoutées dynamiquement ici -->
	</select>
	<button id="btn-ajouter-etudiant">Ajouter à l'équipe</button>
	<button id="btn-supprimer-etudiant">Supprimer de l'équipe</button>

	<!-- Modifier le nom d'une équipe -->
	<h2>Modifier le nom d'une équipe</h2>
	<label for="select-equipe2">Choisir une équipe :</label>
	<select id="select-equipe2" name="select-equipe2">
		<!-- Les options avec les noms des équipes seront ajoutées dynamiquement ici -->
	</select>
	<label for="nouveau-nom-equipe">Nouveau nom :</label>
	<input type="text" id="nouveau-nom-equipe" name="nouveau-nom-equipe">
	<button id="btn-modifier-nom-equipe">Modifier le nom de l'équipe</button>

	<!-- Exporter les équipes constituées dans un fichier CSV une fois tous les étudiants affectés -->
	<h2>Exporter les équipes constituées</h2>
	<form method="POST" action="Serv1?id=4">
		<button id="btn-exporter-equipes">Exporter les équipes</button>
	</form>
	
	<h1>Changer de page</h1>
	<form method="POST" action="Serv1?id=2">
		 <button name='buttonPage' type='submit' value='Index'>Index Page</button>
	 </form>
</body>
</html>