angular.module('pronostic.controllers.home', [])
.controller("HomeController", ['$scope', '$http',
	function($scope, $http){
	
}]);

angular.module('pronostic.controllers',[
	'pronostic.controllers.calendar',
	'pronostic.controllers.classement',
	'pronostic.controllers.finales',
	'pronostic.controllers.group',
	'pronostic.controllers.login',
	'pronostic.controllers.pronostic',
	'pronostic.controllers.home',
	'pronostic.controllers.admin',
	'pronostic.controllers.user',
	]);

angular.module('pronostic.services',[
	'pronostic.rest.service',
	'pronostic.services.notifier',
	]);

