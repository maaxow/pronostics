angular.module('pronostic.controllers.home', ['pronostic.rest.service'])
	.controller("HomeController", ['$scope', '$user', '$game', '$team', 'PRONO', 
		function($scope, $user, $game, $team, PRONO){
			console.log("HomeController");
//			console.log("Contsntas ", PRONO)
			$scope.getAllUser = function(){
				$team.getByGroupe("A").then(function(response){
					console.log("get groupe A ", response.data)
				}).catch(function(e){
					console.log("error", e);
				});
			}
}]);

angular.module('pronostic',['ui.router', 
	'pronostic.controllers.home', 
	'pronostic.directives.toolbar', 'pronostics.constants'])
	.config(['$locationProvider','$stateProvider','$urlRouterProvider', 'PRONO',
	function($locationProvider, $stateProvider, $urlRouterProvider, PRONO) {
	$stateProvider
	.state('home', {
		url: "/",
		controller : 'HomeController'
	})
	.state('home.pronostic', {
		url: "pronostiques",
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
		templateUrl: 'components/finale/finale.html',
		controller : 'FinaleController'
	})
	.state('home.groupe', {
		url: "groupes",
		templateUrl: 'components/groupe/groupe.html',
		controller : 'GroupController',
	})
	.state('home.login', {
			url: "/login",
			templateUrl: 'components/login/login.html',
			controller: 'LoginController'
	});
	

	// For invalid route
	$urlRouterProvider.otherwise(PRONO.contextPath);
	// use the HTML5 History API
	$locationProvider.html5Mode(true);

}]);