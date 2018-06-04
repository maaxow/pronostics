(function(){
	'use strict';
	var app = angular.module('pronostic',['ui.router'/*, 'pronostic.directives.toolbar'*/]);

	app.config(['$locationProvider','$stateProvider','$urlRouterProvider', 
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

	}])
	.controller("HomeController", ['$scope', function($scope){
		console.log("HomeController");
		$scope.message = "Home conntroller";
	}])
	.directive("pronosticToolbar",[function(){
				return {
					templateUrl : 'directives/toolbar/toolbar.html',
					controller : ['$scope',
							function($scope){
						$scope.message = "Test directive toolbar";
					}]
				}
	}]);
})();