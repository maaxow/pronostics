angular.module('pronostic.controllers.classement',['pronostic.rest.service'])
.controller('ClassementController', ['$scope', '$user', '$login', function($scope, $user, $login){

	$scope.user = null;
	$scope.userId = null;
	
	$scope.allUsers = [];
	$user.getAll().then(function(response){
		$scope.allUsers = response.data;
	});
	
	$scope.isUserLogged = function(user){
		if($scope.userId != null){
			return user.id == $scope.userId;
		}
	}
	
	if($login.isLogged()){
		$scope.user = $login.getUserLogged();
		if($scope.user != null){
			$scope.userId = $scope.user.id;
		}
	}
}]);
	