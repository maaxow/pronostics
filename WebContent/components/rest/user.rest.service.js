;angular.module('pronostic.rest.service.user', [])
.service('$user', ['$http', function($http){
	return {
		getAll : function(){
			return $http.get('rest/user');
		},
		get: function(id){
			return $http.get('rest/user/'+id);
		},
		save : function(user){
			return $http.put('rest/user', user);
		},
		update : function(user){
			return $http.post('rest/user', user);
		},
		updateWithoutPassword : function(userId, firstname, lastname){
			return $http.post('rest/user/' + userId + '/' + firstname + '/' + lastname);
		},
		updatePassword : function(userId, password){
			return $http.post('rest/user/' + userId + '/' + password);
		}
	};
}]);