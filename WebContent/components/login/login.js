(function(){
	var loginController = angular.module('pronostic.controllers.login', []);
	
	loginController.controller('LoginController', [
		'$scope', function($scope){
			$scope.message = "On est bien sur la page de Login !!!";
		}
	]);
});