
angular.module('pronostic.directives.toolbar', ['pronostic.services.login'])
	.directive("pronosticToolbar",['$timeout', function($timeout){
			return {
				templateUrl : 'directives/toolbar/toolbar.html',
				controller : ['$scope', '$login', '$state',
					function($scope, $login, $state){
						$scope.$login = $login;
						
						$scope.user = {};
						$scope.logout = function(){
							$login.logout();
							$state.go('home.login');
						};
						
						$scope.isLoginPage = function(){
							return $state.is('home.login');
						};
						
						$scope.isLogged = function(){
							if($login.isLogged()){
								$scope.user = $login.getUserLogged();
								return true;
							}
							return false;
						};
						
						$scope.isAdmin = function(){
							if($scope.isLogged()){
								return $scope.user.isAdmin;
							}
						};
						
						$scope.gotToUserPage = function(){
							$state.go('home.user');
						}
					}]
			}
}]);
