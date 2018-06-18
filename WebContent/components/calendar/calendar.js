angular.module('pronostic.controllers.calendar',['pronostic.rest.service'])
.controller('CalendarController', ['$scope', '$game', function($scope, $game){
	
	$scope.calendar = {};
	$game.getGameFilterByDate().then(function(response){
//		console.log("response filter", response);
		$scope.calendar = response.data;
	}, function(error){
		console.log("error", error);
	});
}]);
	