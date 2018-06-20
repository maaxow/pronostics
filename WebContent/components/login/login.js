angular.module('pronostic.controllers.login', [])
.controller('LoginController', ['$scope', '$login', '$state', function($scope, $login, $state){
		$scope.message = "On est bien sur la page de Login !!!";
		
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
				$scope.user = userLogged;
				$scope.errorLogging = false;
				$state.go('home.calendar');
			}, function(error){
				console.log("error !!!!", error);
				$scope.errorLogging = true;
				$scope.message = error;
			});
		};
		
		$scope.save = function(){
			$login.saveUser($scope.saveUser);
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
