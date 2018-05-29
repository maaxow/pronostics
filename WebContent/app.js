(function(){
	'use strict';
	angular.module('app',[]);

	app.config(['$locationProvider','$stateProvider','$urlRouterProvider', 'paths', function($locationProvider, $stateProvider, $urlRouterProvider, paths) {
		$stateProvider
		.state('home', {
			url: "/",
			templateUrl: paths.views + '/index.html',
			controller : 'home'
		})
		.state('home.finale', {
			url: "finale",
			views : {
				'main@home' : {
					templateUrl: paths.views + '/finale.html',
					controller : 'finale'
				}
			}
		})
		.state('home.match', {
			url: "match",
			views : {
				'main@home' : {
					templateUrl: paths.views + '/match.html',
					controller : 'match',
				}
			}
		})
		.state('login', {
				url: "/login",
				templateUrl: paths.views + '/login.html',
				controller: 'login'
		});
		

		// For invalid route
		$urlRouterProvider.otherwise('/issue');
		// use the HTML5 History API
		$locationProvider.html5Mode(true);

	}]);
})();
	