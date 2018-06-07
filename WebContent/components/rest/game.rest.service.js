angular.module('pronostic.rest.service.game', [])
.service('$game', ['$http', function($http){
	return {
		getAll : function(){
			return $http.get('/pronostics/rest/game');
		},
		get: function(id){
			return $http.get('/rest/game/'+id);
		},
		save : function(user){
			return $http.put('/rest/game', {user: user});
		},
		update : function(user){
			return $http.post('/rest/game', {user: user});
		}
	};
}]);