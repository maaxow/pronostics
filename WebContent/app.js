angular.module('pronostic.controllers.home', ['pronostic.rest.service.user'])
	.controller("HomeController", ['$scope', '$user',
		function($scope, $user){
			console.log("HomeController");
			$scope.getAllUser = function(){
				$user.getAll();
			}
}]);

angular.module('pronostic',['ui.router', 
	'pronostic.controllers.home', 
	'pronostic.directives.toolbar']).config(['$locationProvider','$stateProvider','$urlRouterProvider', 
	function($locationProvider, $stateProvider, $urlRouterProvider) {
	$stateProvider
	.state('home', {
		url: "/",
		templateUrl: 'index.html',
		controller : 'HomeController'
	})
	.state('home.finale', {
		url: "/finale",
		views : {
			'main@home' : {
				templateUrl: 'components/finale/finale.html',
				controller : 'FinaleController'
			}
		}
	})
	.state('home.match', {
		url: "match",
		views : {
			'main@home' : {
				templateUrl: 'components/match/match.html',
				controller : 'MatchController',
			}
		}
	})
	.state('home.login', {
			url: "/login",
			templateUrl: 'components/login/login.html',
			controller: 'LoginController'
	});
	

	// For invalid route
//		$urlRouterProvider.otherwise('/');
	// use the HTML5 History API
	$locationProvider.html5Mode(true);

}]);