<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team Making</title>
</head>
<body>
<body>
	<h1>Gestion des équipes</h1>


	<h2>Créer des équipes</h2>
	<form action="Serv1?id=4" method="POST">
		<label for="nb-equipes">Nombre d'équipes :</label> <input
			type="number" id="nb-equipes" name="nb-equipes" min="1" max="10"
			required>
		<button id="btn-creer-equipes">Créer les équipes</button>
	</form>

	<c:if test="${not empty teams}">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Nom de l'équipe</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${teams}" var="team">
					<tr>
						<td>${team.idTeam}</td>
						<td>${team.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>



	<h2>Liste des étudiants sans équipe</h2>
	<form action="Serv1?id=5" method="POST">
		<input type="submit" value="Actualiser" />
	</form>
	<c:if test="${not empty studentsWithoutTeam}">
		<table>
			<thead>
				<tr>
					<th>Nom</th>
					<th>Prénom</th>
					<th>Genre</th>
					<th>Site</th>
					<th>Formation</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${studentsWithoutTeam}" var="student">
					<tr>
						<td>${student.name}</td>
						<td>${student.firstName}</td>
						<td><c:choose>
								<c:when test="${student.idGender == 0}">Homme</c:when>
								<c:when test="${student.idGender == 1}">Femme</c:when>
								<c:otherwise>Non spécifié</c:otherwise>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${student.idSite == 0}">Angers</c:when>
								<c:when test="${student.idSite == 1}">Paris</c:when>
								<c:when test="${student.idSite == 2}">Dijon</c:when>
								<c:otherwise>Non spécifié</c:otherwise>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${student.idFormation == 0}">P1</c:when>
								<c:when test="${student.idFormation == 1}">P2</c:when>
								<c:when test="${student.idFormation == 2}">E3e</c:when>
								<c:when test="${student.idFormation == 3}">E4e</c:when>
								<c:when test="${student.idFormation == 4}">E5e</c:when>
								<c:otherwise>Non spécifié</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>


	<h2>Liste des étudiants par équipe</h2>
	<form action="Serv1?id=7" method="POST">
		<button type="submit">Afficher les équipes</button>
	</form>

	<c:if test="${not empty studentsByTeam}">
		<h2>Étudiants par équipe</h2>
		<c:forEach items="${studentsByTeam}" var="student" varStatus="loop">
			<c:if test="${student.idTeam != currentTeam}">
				<c:if test="${not loop.first}">
					</tbody>
					</table>
				</c:if>
				<h3>Équipe ${student.idTeam == 0 ? 'non attribué' : student.idTeam}</h3>
				<table>
					<thead>
						<tr>
							<th>Nom</th>
							<th>Prénom</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${student.name}</td>
							<td>${student.firstName}</td>
						</tr>
						</c:if>
						<c:if test="${student.idTeam == currentTeam}">
							<tr>
								<td>${student.name}</td>
								<td>${student.firstName}</td>
							</tr>
						</c:if>
						<c:if test="${loop.last}">
					</tbody>
				</table>
			</c:if>
			<c:set var="currentTeam" value="${student.idTeam}" />
		</c:forEach>
	</c:if>

	<!-- Proposer une composition automatique des équipes -->
	<h2>Composition automatique des équipes</h2>
	<form method="POST" action="Serv1?id=6">
		<button type="submit" id="btn-composer-equipes">Composer les
			équipes aléatoirement</button>
	</form>
	<c:if test="${not empty errorMessage}">
		<p>${errorMessage}</p>
	</c:if>
	<br>
	<form method="POST" action="Serv1?id=13">
		<button type="submit" id="btn-composer-equipes">Composer les
			équipes par ordre alphabétique</button>
	</form>
	<c:if test="${not empty errorMessage}">
		<p>${errorMessage}</p>
	</c:if>






	<h2>Ajouter/supprimer étudiant à l'équipe</h2>

	<form action="Serv1?id=9" method="POST">
		<input type="submit" value="Afficher les étudiants et les équipes" />
	</form>
	<br />
	<form method="POST" action="Serv1?id=8">
		<label> Ajouter un étudiant :</label> <br>
		<label> Étudiant :</label> <select name="studentId">
			<option value="">Sélectionner un étudiant</option>
			<c:forEach items="${studentsWithoutTeam}" var="student">
				<option value="${student.idStudent}">${student.firstName}
					${student.name}</option>
			</c:forEach>
		</select> <label>Équipe :</label> <select name="teamId">
			<option value="">Sélectionner une équipe</option>
			<c:forEach items="${teams}" var="team">
				<option value="${team.idTeam}">${team.name}</option>
			</c:forEach>
		</select><input type="submit" value="Ajouter à l'équipe" />
	</form>
	<br />
	<label> Retirer étudiant de l'équipe :</label> <br>
	<form action="Serv1?id=10" method="POST">
		<select name="studentId">
			<option value="">Sélectionner un étudiant</option>
			<c:forEach var="student" items="${studentsByTeam}">
				<c:if test="${student.idTeam ne 0}">
					<option value="${student.idStudent}">${student.firstName}
						${student.name}</option>
				</c:if>
			</c:forEach>
		</select> <input type="submit" value="Enlever de l'équipe" />
	</form>


	<h2>Modifier le nom d'une équipe</h2>
	<form method="POST" action="Serv1?id=12">
		<label for="idTeam">Sélectionner l'équipe :</label>
		<select id="idTeam" name="idTeam">
			<c:forEach var="team" items="${teams}">
				<option value="${team.idTeam}">${team.name}</option>
			</c:forEach>
		</select>
		<br>
		<label for="newName">Nouveau nom :</label>
		<input type="text" id="newName" name="newName">
		<br>
		<input type="submit" value="Modifier">
	</form>

	

	<h2>Exporter les équipes constituées</h2>
  <form method="POST" action="Serv1?id=11">
		<button id="btn-exporter-equipes">Exporter les équipes</button>
	</form>

	<h2>Changer de page</h2>
	<form method="POST" action="Serv1?id=2">
		<button name='buttonPage' type='submit' value='Index'>Index
			Page</button>
	</form>
</body>
</html>