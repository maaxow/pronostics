angular.module('pronostic.rest.service.game', [])
.service('$game', ['$http', function($http){
	return {
		getAll : function(){
			return $http.get('rest/game');
		},
		get: function(id){
			return $http.get('rest/game/'+id);
		},
		save : function(user){
			return $http.put('rest/game', {user: user});
		},
		update : function(game){
			return $http.post('rest/game', game);
		},
		getGameExceptList: function(idExcepted){
			return $http.post('rest/game/except', idExcepted);
		},
		getGameFilterByDate: function(){
			return $http.get('rest/game/filter/date');
		},
		getGameNotScored: function(){
			return $http.get('rest/game/not/scored');
		},
		getFinaleGame: function(){
			return $http.get('rest/game/finales');
		}
	};
}]);