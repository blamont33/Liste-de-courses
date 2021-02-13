<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer une recette</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/ListeDeCourses/css/creerRecette.css"
	type="text/css">
</head>

<div class="container">
	<nav class="navbar navbar-expand-sm bg-info navbar-dark">
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link" href="Accueil">Mes
					recettes</a></li>
			<li class="nav-item"><a class="nav-link" href="CreerRecette">Créer
					une recette</a></li>
			<li class="nav-item"><a class="nav-link" href="Menu">Menu</a></li>
			<li class="nav-item"><a class="nav-link" href="Liste">Ma
					liste de courses</a></li>
		</ul>
	</nav>
	<body>
		<form method="post" id="nomRecette" class="form-inline"
			action="/ListeDeCourses/CreerRecette">
			<label for="nomRecette" class="mr-sm-2">Nom de la recette : </label>
			<input type="text" class="form-control mr-sm-2" id="nomRecette"
				name="nomRecette" required="required"> 
				<label for="lien" class="mr-sm-2">Lien : </label>
			<input type="text" class="form-control mr-sm-2" id="lien"
				name="lien">
				<input type="submit"
				class="btn btn-info" value="Valider la recette" name="choix">
		</form>

		<h3>Liste des ingrédients</h3>
		<p>Sélectionnez les ingrédient à insérer dans la recette</p>
		<input class="form-control" id="myInput" type="text"
			placeholder="Rechercher..">
		</br>

		<div class="row">
			<div class="col" id="listeCreer">
				<c:forEach var="liste" items="${listeIngredient}">
					<form method="post" action="/ListeDeCourses/CreerRecette">

						<ul class="list-group" id="myList">
							<li class="list-group-item">${liste.nomIngredient}<input
								type="hidden" name="nom" value="${liste.nomIngredient}">
								<button type="submit" class="btn btn-info float-right"
									value=ajouterIngredient name="choix" id="ajouter">Ajouter</button>
								<input id="id" name="id" type="hidden" value="${liste.id}">
								<select name="unite" class="selectpicker float-right" id="unite">
									<option>g</option>
									<option>cl</option>
									<option>qte</option>
									<option>cas</option>
									<option>cac</option>
									<option>pincée</option>
							</select> <input class="float-right" type="number" id="quantite"
								name="quantite" required="required"> <label
								class="float-right" for="quantite">Quantité : </label>
							</li>
						</ul>
					</form>
				</c:forEach>
			</div>
		</div>
<div class="row">
			<div class="col">
		<form class="form-inline float-right" method="post"
			action="CreerRecette">
			<input type="text" class="form-control" placeholder="Ingrédient"
				id="creerIngredient" name="nomIngredient"> <input
				type="submit" class="btn btn-info" value="Créer un ingrédient"
				name="choix">
		</form>
</div>
</div>

		<div class="row">
			<div class="col">

				<c:forEach var="ingredient" items="${listeIngredients}">
					<form method="post" action="CreerRecette">
						<table>
							<td>-Ingrédient : ${ingredient.nomIngredient} - Quantité : ${ingredient.quantite} 
								${ingredient.unite} <input type="submit" id="buttonx" class="btn btn-outline-danger"
								value="X" name="choix" > <br> <input
								name="supprimer" type="hidden" value="${ingredient.id}">
							</td>

						</table>
					</form>
				</c:forEach>

			</div>
		</div>


		<script>
			$(document)
					.ready(
							function() {
								$("#myInput")
										.on(
												"keyup",
												function() {
													var value = $(this).val()
															.toLowerCase();
													$("#myList li")
															.filter(
																	function() {
																		$(this)
																				.toggle(
																						$(
																								this)
																								.text()
																								.toLowerCase()
																								.indexOf(
																										value) > -1)
																	});
												});
							});
		</script>



	</body>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
</html>