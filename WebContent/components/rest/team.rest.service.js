angular.module('pronostic.rest.service.team', [])
.service('$team', ['$http', function($http){
	return {
		getAll : function(){
			return $http.get('rest/team');
		},
		get: function(id){
			return $http.get('rest/team/'+id);
		},
		getByGroupe: function(groupe){
			return $http.get('rest/team/groupe/'+groupe);
		},
		save : function(team){
			return $http.put('rest/team', {team: team});
		},
		update : function(team){
			return $http.post('rest/team', {team: team});
		},
		updatePoints : function(){
			return $http.get('rest/team/update/score');
		}
	};
}]);