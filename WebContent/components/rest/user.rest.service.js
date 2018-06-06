angular.module('pronostic.rest.service.user', [])
.service('$user', ['$http', function($http){
	return {
		getAll : function(){
			$http.get('/rest/user').then(function(response){
				console.log("$user.getAll() = ", response.data);
			});
		}
	};
}]);