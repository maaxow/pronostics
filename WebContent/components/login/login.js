angular.module('pronostic.controllers.login', [])
.controller('LoginController', ['$scope', '$login', '$state', '$notifier',
	function($scope, $login, $state, $notifier){
		
		$scope.login = "";
		$scope.password = "";
		$scope.user = {
				firstname : "",
				lastname: ""
		};
		$scope.saveUser = {
				username: '',
				password: '',
				firstname: '',
				lastname: ''
		};
		
		$scope.isLoggingForm = true;
		
		$scope.logging = function(login, password){
			$login.authenticate(login, password).then(function(userLogged){
				$notifier.success("Authentification reussi");
				$scope.user = userLogged;
				$state.go('home.calendar');
			}, function(error){
				console.log("error !!!!", error);
				$notifier.error("Erreur a la connexion");
			});
		};
		
		$scope.save = function(){
			$login.saveUser($scope.saveUser).then(function(){
				$notifier.success("Creation du compte reussi");
			}, function(error){
				$notifier.error("Errur lors de la creation");
				console.log("error");
			});
			$scope.switchTo('connect');
		}
		$scope.switchTo = function(mode){
			if(mode === 'subscribe') {
				$scope.isLoggingForm = false;
			}
			else {
				$scope.isLoggingForm = true;
			}
		};
		
}]);
