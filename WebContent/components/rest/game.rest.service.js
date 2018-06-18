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
		update : function(user){
			return $http.post('rest/game', {user: user});
		},
		getGameExceptList: function(idExcepted){
			return $http.post('rest/game/except', idExcepted);
		},
		getGameFilterByDate: function(){
			return $http.get('rest/game/filter/date');
		}
	};
}]);