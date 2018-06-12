angular.module('pronostic.controllers',[
	'pronostic.controllers.calendar',
	'pronostic.controllers.classement',
//	'pronostic.controllers.finale',
	'pronostic.controllers.group',
	'pronostic.controllers.login',
	'pronostic.controllers.pronostic',
	'pronostic.controllers.home',
	]);


angular.module('pronostic.controllers.home', ['pronostic.rest.service'])
.controller("HomeController", ['$scope', '$user', '$game', '$team', 'PRONO', 
	function($scope, $user, $game, $team, PRONO){
		console.log("HomeController");
//		console.log("Contsntas ", PRONO)
		$scope.getAllUser = function(){
			$team.getByGroupe("A").then(function(response){
				console.log("get groupe A ", response.data)
			}).catch(function(e){
				console.log("error", e);
			});
		}
}]);
	