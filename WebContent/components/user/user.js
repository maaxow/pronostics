angular.module('pronostic.controllers.user',['pronostic.rest.service'])
.controller('UserController', ['$scope', '$user', '$login','$state', function($scope, $user, $login, $state){
//	$scope.user = {};
	$scope.newPassword = "";
	$scope.newFirstName = "";
	$scope.newLastname = "";
	
	
	
	$scope.updateUser = function(){
		if($scope.newFirstname == ""){
			// Feedback
		}
		if($scope.newLastname == ""){
			// Feedback
		}
		
		$user.updateWithoutPassword($scope.user.id, $scope.newFirstname, $scope.newLastname).then(function(response){
			// Feedback
			$state.reload();
		})
	};
	$scope.updateUserPassword = function(){
		if($scope.newPassword == ""){
			// Feedback
		}
		$user.updatePassword($scope.user.id, $scope.newPassword).then(function(response){
			// Feedback
		})
	};
	if($login.isLogged()){
		$scope.user = $login.getUserLogged();
		$scope.newFirstname = $scope.user.firstname;
		$scope.newLastname = $scope.user.lastname;
		if($scope.user != null){
			$scope.userId = $scope.user.id;
		}
		else {
			$state.go('home.login');
		}
	} else {
		$state.go('home.login');
	}
	
}]);
	