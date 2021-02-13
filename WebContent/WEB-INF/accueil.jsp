<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>Accueil</title>
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

		<h3>Liste des recettes</h3>
		<p>Sélectionnez vos recettes</p>
		<input class="form-control" id="myInput" type="text"
			placeholder="Rechercher..">
		</br>

		<div class="row">
			<div class="col" id="listeIngredient">
				<c:forEach var="recette" items="${AllRecettes}">
					<form  method="post" action="Menu">

						<ul class="list-group" id="myList">
							<li class="list-group-item">${recette.nomRecette}<a
								href="${recette.lien}" target="_blank" id="lienAccueil"
								class="btn btn-info float-right" role="button">Lien vers la
									recette</a>
									<input type="hidden" value="${recette.lien}" name="lien">
								<button type="submit" class="btn btn-info float-right mr-sm-2"
									value=ajouterRecette name="choix" id="ajouter">Ajouter</button>
								<input id="id" name="id" type="hidden" value="${recette.id}">
								<input type="hidden" value="${recette.nomRecette}"
								name="nomRecette">

							</li>
						</ul>
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


		<%-- <form method="post" action="Accueil">
		<c:set var="num" scope="session" value="${0}" />
		<c:forEach var="recette" items="${AllRecettes}">

			<div id="accordion">
				<div class="card">
					<div class="card-header ">

						<a class="card-link" data-toggle="collapse"
							href="#collapseOne${num}" > ${recette.nomRecette} </a> <input
							type="submit" class="btn btn-info float-right"
							value="Ajouter à la liste">
							<input type="hidden" value="${recette.nomRecette}" name="recette" >

					</div>

					<div id="collapseOne${num}" class="collapse"
						data-parent="#accordion">
						<div class="card-body">
							<c:forEach var="ingredient" items="${recette.listeIngredient}">
						${ingredient.quantite} ${ingredient.unite} ${ingredient.nomIngredient} |
					</c:forEach>

						</div>
					</div>

				</div>
			</div>
			<c:set var="num" scope="session" value="${num+1}" />
		</c:forEach>
</form> --%>
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