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
		<div class="row" id="listeListe">
			<div class="col">
			<form method="post" action="Liste">
				<c:forEach var="ingredient" items="${listeFinale}">
					<table class="table">
						<td><input type="checkbox">
							${ingredient.nomIngredient} - ${ingredient.quantite}
							${ingredient.unite}<input
									type="submit" id="buttonx2" class="btn btn-danger float-right" value="X"
									name="choix"><input name="supprimer"
									type="hidden" value="${ingredient.id}"></td>
					</table>
				</c:forEach>
				</form>
			</div>
		</div>
		<form id="formListe" method="post" action="Liste"
			class="form-inline float-right">
			<div class="form-group">
				<select name="nom" class="form-control " id="sel1">
					<c:forEach var="option" items="${ListeComplete}">
						<option>${option.nomIngredient}</option>
					</c:forEach>
				</select> <label class="float-right" for="quantite">Quantité : </label> <input
					class="float-right" type="number" id="quantite" name="quantite"
					required="required"> <select name="unite"
					class="selectpicker float-right mr-sm-2" id="unite">
					<option>g</option>
					<option>cl</option>
					<option>qte</option>
					<option>cas</option>
					<option>cac</option>
				</select> <input type="submit" class="btn btn-info"
					value="Ajouter à la liste" name="ajouter">
			</div>
		</form>
</body>
</html>