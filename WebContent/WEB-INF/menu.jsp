<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<link rel="stylesheet" href="/ListeDeCourses/css/creerRecette.css"
	type="text/css">
</head>

<title>Insert title here</title>
</head>
<body>
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


		<form id="showMenu" class="form-inline" method="post" action="Menu">
			<div class="form-group">
				<select name="date" class="form-control" id="sel1">
					<c:forEach var="menu" items="${listeMenus}">
						<option value="${menu.date}"><tags:localDate
								date="${menu.date}" /></option>

					</c:forEach>
				</select>
				<button type="submit" class="btn btn-info" value="afficher"
					name="afficher">Afficher le menu</button>
			</div>


		</form>


		<div id="listeMenu" class="row">
			<div class="col">
				<c:set var="num" scope="session" value="${0}" />

				<c:forEach var="recette" items="${listeRecette}">
					<form method="post" action="Menu">
						<div id="accordion">
							<div class="card">
								<div class="card-header ">

									<a class="card-link" data-toggle="collapse"
										href="#collapseOne${num}"> ${recette.nomRecette} </a><input
										type="submit" id="buttonx2"
										class="btn btn-outline-danger float-right" value="X"
										name="choix"><a href="${recette.lien}" target="_blank"
										id="lienAccueil" class="btn btn-info float-right mr-sm-2 "
										role="button">Lien vers la recette</a> <input name="supprimer"
										type="hidden" value="${recette.id}">
								</div>

								<div id="collapseOne${num}" class="collapse"
									data-parent="#accordion">
									<div class="card-body">
										<c:forEach var="ingredient" items="${recette.listeIngredient}">
											<ul>
												<li>${ingredient.quantite}${ingredient.unite}
													${ingredient.nomIngredient}</li>
											</ul>
										</c:forEach>

									</div>
								</div>

							</div>
						</div>
					</form>
					<c:set var="num" scope="session" value="${num+1}" />
				</c:forEach>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<form method="post" action="Menu">
					<button type="submit" class="btn btn-success float-right mr-sm-2"
						value="enregistrer" name="choix" onclick="menuSaved()">Enregistrer le menu</button>
				</form>
				<script>
					function menuSaved() {
						alert("Le menu a bien été enregistré");
					}
				</script>

				<form method="post" action="Liste">
					<a href="Accueil" class="btn btn-info" role="button">Ajouter
						une autre recette</a> <input type="hidden" value="${listeRecette}"
						name="listeRecette">
					<button type="submit" class="btn btn-info float-right mr-sm-2"
						value="generer" name="choix">Générer la liste de courses</button>
				</form>


			</div>
		</div>
	</div>
</body>
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