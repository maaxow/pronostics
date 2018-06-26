
angular.module('pronostic',['ui.router', 
	'pronostic.services', 
	'pronostic.controllers', 
	'pronostic.directives.toolbar', 
	'pronostics.constants'])
	.config(['$locationProvider','$stateProvider','$urlRouterProvider', 'PRONO',
	function($locationProvider, $stateProvider, $urlRouterProvider, PRONO) {
	$stateProvider
	.state('home', {
		url: "",
		controller : 'HomeController'
	})
	.state('home.pronostic', {
		url: "/pronostiques",
		templateUrl: 'components/pronostic/pronostic.html',
		controller : 'PronosticController',
	})
	.state('home.calendar', {
		url: "/calendar",
		templateUrl: 'components/calendar/calendar.html',
		controller : 'CalendarController'
	})
	.state('home.classement', {
		url: "/classement",
		templateUrl: 'components/classement/classement.html',
		controller : 'ClassementController'
	})
	.state('home.finale', {
		url: "/finale",
		templateUrl: 'components/finales/finales.html',
		controller : 'FinaleController'
	})
	.state('home.groupe', {
		url: "/groupes",
		templateUrl: 'components/groupe/groupe.html',
		controller : 'GroupController',
	})
	.state('home.login', {
			url: "/login",
			templateUrl: 'components/login/login.html',
			controller: 'LoginController'
	})
	.state('home.admin', {
		url: "/admin",
		templateUrl: 'components/admin/admin.html',
		controller: 'AdminController'
	})
	.state('home.user', {
		url: "/user",
		templateUrl: 'components/user/user.html',
		controller: 'UserController'
	});
	

	// For invalid route
	$urlRouterProvider.otherwise("/");
	// use the HTML5 History API
	$locationProvider.html5Mode(true);

}]);