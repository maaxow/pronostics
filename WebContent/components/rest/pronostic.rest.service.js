angular.module('pronostic.rest.service.prono', [])
.service('$prono', ['$http', function($http){
	return {
		getAll : function(){
			return $http.get('rest/pronostic');
		},
		get: function(id){
			return $http.get('rest/pronostic/'+id);
		},
		getByUser: function(userId){
			return $http.get('rest/pronostic/user/'+userId);
		},
		save : function(pronostic){
			return $http.post('rest/pronostic/new', pronostic);
		},
		update : function(pronostic){
			return $http.post('rest/pronostic', pronostic);
		},
		updatePoints : function(){
			console.log("update points");
			return $http.get('rest/pronostic/update/points');
		}
	};
}]);