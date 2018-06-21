angular.module('pronostic.controllers.admin',['pronostic.rest.service'])
.controller('AdminController', ['$scope', '$team','$login','$state', '$game', function($scope, $team, $login, $state, $game){
	$scope.user = {};
	$scope.gameToDo = [];
	
	if($login.isLogged()){
		$scope.user = $login.getUserLogged();
		if($scope.user != null){
			$scope.userId = $scope.user.id;
		}
		else {
			$state.go('home.login');
		}
	} else {
		$state.go('home.login');
	}
	
	
	$scope.updateGameToDo = function(){
		$game.getGameNotScored().then(function(response){
			$scope.gameToDo = response.data;
		});
	};
	$scope.updateGameToDo();
	
	$scope.updatePoints = function(){
		$team.updatePoints();
	};
	
	$scope.validateScore = function(game){
		if(game.goalTeam1 >= 0 && game.goalTeam2 >= 0){
			$game.update(game).then(function(response){
				$scope.updateGameToDo();
			});
		}
	}
}]);
	