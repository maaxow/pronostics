angular.module('pronostic.services.login', ['ngCookies'])
.service('$login', ['$cookies', '$http', '$q', function($cookies, $http, $q){
	
	function User(){
			this.id = null,
			this.username = null,
			this.firstname = null,
			this.lastname = null,
			this.point = null,
			this.isAdmin = false;
	}
	function User(id, username, firstname, lastname, point, isAdmin){
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.point = point;
		this.isAdmin = isAdmin;
	}
	var _user = null,
	_isLogged = false;
	
	return {
		isLogged : function(){
//			var token = $cookies.get("X-TOKEN");
//			var defer = $q.defer();
//			if(token != null){
//				$http.post('rest/auth/token/valid', {login: login, token, token}).then(function(response){
//					console.log("response ", response.status);
//					if(response.status === 200){
//						defer.resolve(true);
//					}
//					else {
//						defer.reject(false);
//					}
//				});
//			} else {
//				defer.reject(false);
//			}
//			return defer.promise;
			
			return _isLogged;
		},
		authenticate: function(login, password){
			var defer = $q.defer();
			$http.post('rest/auth/authenticate', {login: login, password: password}).then(function(response){
				if(response.status === 200){
					var userTmp = response.data;
					var isAdmin = userTmp.role === 'ADMIN' ? true : false;
					_user = new User(userTmp.id,userTmp.username, userTmp.firstname, userTmp.lastname, userTmp.point, isAdmin);
					_isLogged = true;
					//TODO add point calcul
					defer.resolve(_user);
				} else {
					defer.reject();
				}
			});
			return defer.promise;
		},
		logout : function(){
			_user = null;
			_isLogged = false;
		},
		saveUser: function(user){
			console.log("trying to save user :", user);
			$http.put('rest/user', user).then(function(response){
				console.log("response saving : ", response);
			}, function(error){
				console.log("error ! ", error);
			});
		},
		getUserLogged: function(){
//			return new User(5,"maaxow", "Maxime", "Rose", 150, true);
			if(_isLogged){
				return _user;
			} else {
				return null;
			}
		}
	};

}]);