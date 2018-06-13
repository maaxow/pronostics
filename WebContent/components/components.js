angular.module('pronostic.controllers.home', ['pronostic.rest.service'])
.controller("HomeController", ['$scope',
	function($scope){
	
//	$login.isLogged();
}]);

angular.module('pronostic.controllers',[
	'pronostic.controllers.calendar',
	'pronostic.controllers.classement',
	'pronostic.controllers.finales',
	'pronostic.controllers.group',
	'pronostic.controllers.login',
	'pronostic.controllers.pronostic',
	'pronostic.controllers.home',
	]);

