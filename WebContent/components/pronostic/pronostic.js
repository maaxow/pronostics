angular.module('pronostic.controllers.pronostic',['pronostic.rest.service'])
.controller('PronosticController', ['$scope', '$login', '$http', function($scope, $login, $http){

	$scope.userId = null;
	$scope.pronoToDo = [];
	$scope.pronoDone = [];
	$scope.but = [0,1,2,3,4,5,6,7,8,9,10]
	
	$login.authenticate("maaxow","maaxow").then(function(userLogged){
		var user = userLogged;
		$scope.user = user;
		$scope.userId = user.id;
		$scope.updatePronoDone();
		$scope.updatePronoToDo();
	});
	
	
	$scope.updatePronoToDo = function(){
		if($scope.userId){
			$scope.updatePronoDone().then(function(){
				console.log("updatePronoDone done", $scope.pronoDone);
				var pronoDoneIdList = $scope.pronoDone.map(function(item){
					return item.id;
				});
				console.log("updatePronoDone done", pronoDoneIdList);
				pronoDoneIdList = pronoDoneIdList.length === 0 ? null : pronoDoneIdList;
				// list a recup avec les prono deja fait par le mec
				$http.post('rest/game/except', pronoDoneIdList).then(function(response){
					console.log("response list game to prono", response.data);
					$scope.pronoToDo = response.data;
				});
			});
		}
	};
	$scope.updatePronoDone = function(){
		return $http.get('rest/pronostic/user/'+$scope.userId).then(function(response){
			$scope.pronoDone = response.data;
		});
	};
	
	$scope.isAfterNow = function(date){
		return date < new Date().getTime();
	};
	
	$scope.validateProno = function(game){
		var goalTeam1 = game.goalTeam1;
		delete game.goalTeam1;
		var goalTeam2 = game.goalTeam2;
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
		console.log("saving prono :", prono);
		$http.post('rest/pronostic/new', prono).then(function(response){
			console.log("saves prono :", response);
			$scope.updatePronoToDo();
		})
	}
}]);
	