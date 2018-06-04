(function(){
	'use strict';
	var app = angular.module('pronostic',['pronostic.directives.toolbar']);

	app.config(['$locationProvider','$stateProvider','$urlRouterProvider', 'paths', function($locationProvider, $stateProvider, $urlRouterProvider, paths) {
		$stateProvider
		.state('home', {
			url: "/",
			templateUrl: paths.views + '/index.html',
			controller : 'HomeController'
		})
		.state('home.finale', {
			url: "finale",
			views : {
				'main@home' : {
					templateUrl: paths.views + '/finale.html',
					controller : 'FinaleController'
				}
			}
		})
		.state('home.match', {
			url: "match",
			views : {
				'main@home' : {
					templateUrl: paths.views + '/match.html',
					controller : 'MatchController',
				}
			}
		})
		.state('login', {
				url: "/login",
				templateUrl: 'components/login/login.html',
				controller: 'LoginController'
		});
		

		// For invalid route
		$urlRouterProvider.otherwise('/issue');
		// use the HTML5 History API
		$locationProvider.html5Mode(true);

	}]);
})();
	