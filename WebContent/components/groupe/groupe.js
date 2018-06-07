angular.module('pronostic.controllers.group',['pronostic.rest.service'])
.controller('GroupController', ['$scope', '$team', function($scope, $team){
	$scope.groups = {
		A : [],	
		B : [],	
		C : [],	
		D : [],	
		E : [],	
		F : [],	
		G : [],	
		H : []	
	};
	$team.getByGroupe("A").then(function(response){
		$scope.groups.A = response.data;
	});
	$team.getByGroupe("B").then(function(response){
		$scope.groups.B = response.data;
	});
	$team.getByGroupe("C").then(function(response){
		$scope.groups.C = response.data;
	});
	$team.getByGroupe("D").then(function(response){
		$scope.groups.D = response.data;
	});
	$team.getByGroupe("E").then(function(response){
		$scope.groups.E = response.data;
	});
	$team.getByGroupe("F").then(function(response){
		$scope.groups.F = response.data;
	});
	$team.getByGroupe("G").then(function(response){
		$scope.groups.G = response.data;
	});
	$team.getByGroupe("H").then(function(response){
		$scope.groups.H = response.data;
	});
}]);
	