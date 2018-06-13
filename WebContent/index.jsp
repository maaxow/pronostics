<!DOCTYPE html>
<html>
<head>
	<title>Pronostics</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- 	<meta charset="ISO-8859-1"> -->
	<script src="lib/jquery-3.3.1/jquery-3.3.1.min.js"></script>
	<script src="lib/bootstrap-4.1.1/js/bootstrap.min.js"></script>
	<script src="lib/angularjs-1.6.10/angular.min.js"></script>
	<script src="lib/angular-cookies/angular-cookies.min.js"></script>
	<script src="lib/angularjs-1.6.10/angular-ui-router.js"></script>
	<link rel="stylesheet" href="lib/bootstrap-4.1.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/material-icons/material-icons.css">
	<link rel="stylesheet" href="css/main.css">
	
<!-- 	REST SERVICE -->
	<script src="components/rest/user.rest.service.js"></script>
	<script src="components/rest/game.rest.service.js"></script>
	<script src="components/rest/team.rest.service.js"></script>
	<script src="components/rest/rest.service.js"></script>
	<script src="components/login/login.service.js"></script>
<!-- 	DIRECTIVES -->
	<script src="directives/toolbar/toolbar.js"></script>
<!-- 	COMPONENTS -->
	<script src="components/login/login.js"></script>
	<script src="components/groupe/groupe.js"></script>
	<script src="components/calendar/calendar.js"></script>
	<script src="components/classement/classement.js"></script>
	<script src="components/finales/finales.js"></script>
	<script src="components/pronostic/pronostic.js"></script>
	<script src="components/components.js"></script>
	<script src="js/main.js"></script>
	<script src="constants.js"></script>
	<script src="app.js"></script>
	
	<base href="${pageContext.request.contextPath}/" />
</head>
<jsp:include page="index.html" />
</html>