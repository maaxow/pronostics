angular.module('pronostic.controllers.pronostic',['pronostic.rest.service'])
.controller('PronosticController', ['$scope', '$login', '$game', '$http', '$state', '$prono', '$notifier',
	function($scope, $login, $game, $http, $state, $prono, $notifier){

	$scope.userId = null;
	$scope.pronoToDo = [];
	$scope.pronoDone = [];
	$scope.but = [0,1,2,3,4,5,6,7,8,9,10]
	
	$scope.updatePronoToDo = function(){
		if($scope.userId){
			$scope.updatePronoDone().then(function(){
				var pronoDoneIdList = $scope.pronoDone.map(function(item){
					return item.game.id;
				});
				pronoDoneIdList = pronoDoneIdList.length === 0 ? null : pronoDoneIdList;
				// list a recup avec les prono deja fait par le mec
				$game.getGameExceptList(pronoDoneIdList).then(function(response){
					$scope.pronoToDo = response.data;
				});
			});
		}
	};
	$scope.updatePronoDone = function(){
		return $prono.getByUser($scope.userId).then(function(response){
			$scope.pronoDone = response.data;
		});
	};
	
	$scope.isAfterNow = function(date){
		return date < new Date().getTime();
	};
	
	$scope.validateProno = function(game){
		var goalTeam1 = game.goalTeam1;
		var goalTeam2 = game.goalTeam2;
		
		if(goalTeam1 != -1 && goalTeam2 != -1){
			delete game.goalTeam1;
			delete game.goalTeam2;
			var prono = {
					game: game,
					user: {
						id: $scope.user.id,
						username:$scope.user.username,
						password:$scope.user.password,
						firstname:$scope.user.firstname,
						lastname:$scope.user.lastname,
						role:$scope.user.isAdmin?'ADMIN':'USER',
					},
					goalTeam1: goalTeam1,
					goalTeam2: goalTeam2
			}
			$prono.save(prono).then(function(response){
				$notifier.success("Pronostique valide");
				$scope.updatePronoToDo();
			}, function(){
				$notifier.error("Erreur Pronostique");
			});
		} else {
			$notifier.error("Erreur Pronostique");
		}
	};
	if($login.isLogged()){
		$scope.user = $login.getUserLogged();
		if($scope.user != null){
			$scope.userId = $scope.user.id;
			$scope.updatePronoDone();
			$scope.updatePronoToDo();
		}
		else {
			$state.go('home.login');
		}
	} else {
		$state.go('home.login');
	}
}]);
	