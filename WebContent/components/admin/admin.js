angular.module('pronostic.controllers.admin',['pronostic.rest.service'])
.controller('AdminController', ['$scope', '$team','$login','$state', '$game', '$user', '$prono', 
	function($scope, $team, $login, $state, $game, $user, $prono){
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
	};
	
	$scope.updateClassement = function(){
		$prono.updatePoints().then(function(response){
			$user.updateClassement().then(function(response2){
				// nothing to do
				$notifier.success("Classement mis a jour");
			});
		});
	};
	
	$scope.pronos = [];
	$scope.updatePronoDone = function(){
		$prono.getAll().then(function(reponse){
			console.log("reponse : ", reponse);
			$scope.pronos = reponse.data;
		})
	};
	$scope.updatePronoDone();
	
	/**  
	 * return 1 or 2
	 */
	$scope.isWinner = function(res){
		if(res.goalTeam1 > res.goalTeam2){
			return 1;
		} else if(res.goalTeam1 < res.goalTeam2){
			return 2;
		} else {
			return 0;
		}
	};
}]);
	