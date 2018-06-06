angular.module('pronostic.rest.service.team', [])
.service('$team', ['$http', 'PRONO', function($http, PRONO){
	var restUrl = PRONO.contextPath + "/rest";
	return {
		getAll : function(){
			return $http.get(restUrl + '/team');
		},
		get: function(id){
			return $http.get(restUrl + '/team/'+id);
		},
		getByGroupe: function(groupe){
			return $http.get(restUrl + '/team/groupe/'+groupe);
		},
		save : function(team){
			return $http.put(restUrl + '/team', {team: team});
		},
		update : function(team){
			return $http.post(restUrl + '/team', {team: team});
		}
	};
}]);