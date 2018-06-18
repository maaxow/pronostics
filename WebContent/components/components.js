angular.module('pronostic.controllers.home', ['pronostic.rest.service'])
.controller("HomeController", ['$scope', '$http', 
	function($scope, $http){
	
//	$http.get('rest/update/score/2/0-1').then(function(response){
//		console.log("response : ", response);
//	})
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

